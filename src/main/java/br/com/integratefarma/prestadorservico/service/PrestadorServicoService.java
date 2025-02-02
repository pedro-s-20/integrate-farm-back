package br.com.integratefarma.prestadorservico.service;

import br.com.integratefarma.agendamento.dto.AgendamentoListaDTO;
import br.com.integratefarma.agendamento.dto.AgendamentoPrestadorServicoRelatorioDTO;
import br.com.integratefarma.agendamento.service.AgendamentoService;
import br.com.integratefarma.email.enums.TipoEmail;
import br.com.integratefarma.email.service.EmailService;
import br.com.integratefarma.especialidade.service.EspecialidadeService;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCreateDTO;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoUpdateDTO;
import br.com.integratefarma.prestadorservico.entity.PrestadorServicoEntity;
import br.com.integratefarma.prestadorservico.repository.PrestadorServicoRepository;
import br.com.integratefarma.usuario.dto.UsuarioCreateDTO;
import br.com.integratefarma.usuario.entity.UsuarioEntity;
import br.com.integratefarma.usuario.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PrestadorServicoService {
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
    private final EspecialidadeService especialidadeService;
    private final AgendamentoService agendamentoService;
    private final EmailService emailService;

    public PrestadorServicoService(PrestadorServicoRepository prestadorServicoRepository,
                                   ObjectMapper objectMapper,
                                   UsuarioService usuarioService,
                                   EspecialidadeService especialidadeService,
                                   @Lazy AgendamentoService agendamentoService,
                                   EmailService emailService) {
        this.prestadorServicoRepository = prestadorServicoRepository;
        this.objectMapper = objectMapper;
        this.usuarioService = usuarioService;
        this.especialidadeService = especialidadeService;
        this.agendamentoService = agendamentoService;
        this.emailService = emailService;
    }



    public PrestadorServicoCompletoDTO recuperarPrestadorServico() throws RegraDeNegocioException {
        PrestadorServicoEntity prestadorServicoEntity = prestadorServicoRepository.getPrestadorServicoEntityByIdUsuario(usuarioService.getIdLoggedUser());
        return getById(prestadorServicoEntity.getIdPrestadorServico());
    }

    public PrestadorServicoCompletoDTO getById(Integer idPrestadorServico) throws RegraDeNegocioException {
        Optional<PrestadorServicoCompletoDTO> prestadorServicoRetornado = prestadorServicoRepository.getByIdPersonalizado(idPrestadorServico);
        if (prestadorServicoRetornado.isEmpty()) {
            throw new RegraDeNegocioException("Usuário não encontrado.");
        }
        return prestadorServicoRetornado.get();
    }

    public PrestadorServicoEntity getPrestadorServico(Integer id) throws RegraDeNegocioException {
        return prestadorServicoRepository.findById(id)
                .filter(prestadorServicoEntity -> prestadorServicoEntity.getUsuarioEntity().getAtivo().equals(1))
                .orElseThrow(() -> new RegraDeNegocioException("Prestador de Servico não existe!"));
    }

    public AgendamentoListaDTO getPrestadorServicoAgentamentos() throws RegraDeNegocioException {
        PrestadorServicoCompletoDTO prestadorServicoCompletoDTO = recuperarPrestadorServico();
        AgendamentoPrestadorServicoRelatorioDTO agendamentoPrestadorServicoRelatorioDTO = agendamentoService.getRelatorioPrestadorServicoById(prestadorServicoCompletoDTO.getIdPrestadorServico());

        return objectMapper.convertValue(agendamentoPrestadorServicoRelatorioDTO, AgendamentoListaDTO.class);
    }

    public PrestadorServicoCompletoDTO adicionar(PrestadorServicoCreateDTO prestadorServico) throws RegraDeNegocioException {
        checarSeTemNumero(prestadorServico.getNome());

        UsuarioEntity usuarioEntity = objectMapper.convertValue(prestadorServico, UsuarioEntity.class);
        usuarioEntity.setIdCargo(2);

        // Adicionando o usuario que foi salvado no PrestadorServico a salvar
        PrestadorServicoEntity prestadorServicoEntity = objectMapper.convertValue(prestadorServico, PrestadorServicoEntity.class);
        usuarioEntity.setPrestadorServicoEntity(prestadorServicoEntity);
        prestadorServicoEntity.setUsuarioEntity(usuarioEntity);

        // Adicionando Convenio em PrestadorServico a salvar
        prestadorServicoEntity.setEspecialidadeEntity(especialidadeService.getEspecialidade(prestadorServico.getIdEspecialidade()));

        usuarioService.validarUsuarioAdicionado(usuarioEntity);
        usuarioService.adicionar(usuarioEntity);

        prestadorServicoRepository.save(prestadorServicoEntity);
        try {
            emailService.sendEmailUsuario(prestadorServicoEntity.getUsuarioEntity(), TipoEmail.USUARIO_CADASTRO, null);
        } catch (MessagingException | TemplateException | IOException e) {
            usuarioService.hardDelete(prestadorServicoEntity.getUsuarioEntity().getIdUsuario());
            throw new RegraDeNegocioException("Erro ao enviar o e-mail. Cadastro não realizado.");
        }

        return getById(prestadorServicoEntity.getIdPrestadorServico());
    }

    public PrestadorServicoCompletoDTO editar(PrestadorServicoUpdateDTO prestadorServico) throws RegraDeNegocioException {
        PrestadorServicoEntity prestadorServicoEntity = objectMapper.convertValue(recuperarPrestadorServico(), PrestadorServicoEntity.class);

        prestadorServicoEntity.setCrm(prestadorServico.getCrm());
        prestadorServicoEntity.setEspecialidadeEntity(especialidadeService.getEspecialidade(prestadorServico.getIdEspecialidade()));
        prestadorServicoEntity.setUsuarioEntity(usuarioService.getUsuario(prestadorServicoEntity.getIdUsuario()));

        checarSeTemNumero(prestadorServico.getNome());

        UsuarioCreateDTO usuarioCreateDTO = objectMapper.convertValue(prestadorServico, UsuarioCreateDTO.class);

        List<PrestadorServicoEntity> listaPrestadorServico = prestadorServicoRepository.findAll().stream()
                .filter(prestadorServicoEntity1 -> !prestadorServicoEntity1.getCrm().equals(prestadorServico.getCrm()))
                .toList();

        for (PrestadorServicoEntity prestadorServicoVerificarCRM : listaPrestadorServico) {
            if (prestadorServico.getCrm().equals(prestadorServicoVerificarCRM.getCrm())) {
                throw new RegraDeNegocioException("CRM já existe!");
            }
        }

        usuarioService.validarUsuarioEditado(usuarioCreateDTO, prestadorServicoEntity.getIdUsuario());
        usuarioService.editar(usuarioCreateDTO, prestadorServicoEntity.getIdUsuario());

        PrestadorServicoEntity prestadorServicoEditado = prestadorServicoRepository.save(prestadorServicoEntity);

        return getById(prestadorServicoEditado.getIdPrestadorServico());
    }

    @Transactional
    public void remover(Integer id) throws RegraDeNegocioException {
        PrestadorServicoEntity prestadorServicoEntity = getPrestadorServico(id);
        usuarioService.remover(prestadorServicoEntity.getIdUsuario());
        agendamentoService.removerPorPrestadorServicoDesativado(prestadorServicoEntity);
    }

    public void checarSeTemNumero(String string) throws RegraDeNegocioException {
        if (string.matches(".*[0-9].*")) { // checa se tem número no nome
            throw new RegraDeNegocioException("O nome da especialidade não pode conter número");
        }
    }


}
