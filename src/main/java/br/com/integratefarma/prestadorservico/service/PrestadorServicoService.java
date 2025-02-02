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



    public PrestadorServicoCompletoDTO recuperarMedico() throws RegraDeNegocioException {
        PrestadorServicoEntity prestadorServicoEntity = prestadorServicoRepository.getMedicoEntityByIdUsuario(usuarioService.getIdLoggedUser());
        return getById(prestadorServicoEntity.getIdPrestadorServico());
    }

    public PrestadorServicoCompletoDTO getById(Integer idMedico) throws RegraDeNegocioException {
        Optional<PrestadorServicoCompletoDTO> medicoRetornado = prestadorServicoRepository.getByIdPersonalizado(idMedico);
        if (medicoRetornado.isEmpty()) {
            throw new RegraDeNegocioException("Usuário não encontrado.");
        }
        return medicoRetornado.get();
    }

    public PrestadorServicoEntity getMedico(Integer id) throws RegraDeNegocioException {
        return prestadorServicoRepository.findById(id)
                .filter(medicoEntity -> medicoEntity.getUsuarioEntity().getAtivo().equals(1))
                .orElseThrow(() -> new RegraDeNegocioException("Prestador de Servico não existe!"));
    }

    public AgendamentoListaDTO getMedicoAgentamentos() throws RegraDeNegocioException {
        PrestadorServicoCompletoDTO prestadorServicoCompletoDTO = recuperarMedico();
        AgendamentoPrestadorServicoRelatorioDTO agendamentoMedicoRelatorioDTO = agendamentoService.getRelatorioMedicoById(prestadorServicoCompletoDTO.getIdPrestadorServico());

        return objectMapper.convertValue(agendamentoMedicoRelatorioDTO, AgendamentoListaDTO.class);
    }

    public PrestadorServicoCompletoDTO adicionar(PrestadorServicoCreateDTO medico) throws RegraDeNegocioException {
        checarSeTemNumero(medico.getNome());

        UsuarioEntity usuarioEntity = objectMapper.convertValue(medico, UsuarioEntity.class);
        usuarioEntity.setIdCargo(2);

        // Adicionando o usuario que foi salvado no Medico a salvar
        PrestadorServicoEntity prestadorServicoEntity = objectMapper.convertValue(medico, PrestadorServicoEntity.class);
        usuarioEntity.setPrestadorServicoEntity(prestadorServicoEntity);
        prestadorServicoEntity.setUsuarioEntity(usuarioEntity);

        // Adicionando Convenio em Medico a salvar
        prestadorServicoEntity.setEspecialidadeEntity(especialidadeService.getEspecialidade(medico.getIdEspecialidade()));

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

    public PrestadorServicoCompletoDTO editar(PrestadorServicoUpdateDTO medico) throws RegraDeNegocioException {
        PrestadorServicoEntity prestadorServicoEntity = objectMapper.convertValue(recuperarMedico(), PrestadorServicoEntity.class);

        prestadorServicoEntity.setCrm(medico.getCrm());
        prestadorServicoEntity.setEspecialidadeEntity(especialidadeService.getEspecialidade(medico.getIdEspecialidade()));
        prestadorServicoEntity.setUsuarioEntity(usuarioService.getUsuario(prestadorServicoEntity.getIdUsuario()));

        checarSeTemNumero(medico.getNome());

        UsuarioCreateDTO usuarioCreateDTO = objectMapper.convertValue(medico, UsuarioCreateDTO.class);

        List<PrestadorServicoEntity> listaMedico = prestadorServicoRepository.findAll().stream()
                .filter(medicoEntity1 -> !medicoEntity1.getCrm().equals(medico.getCrm()))
                .toList();

        for (PrestadorServicoEntity medicoVerificarCRM : listaMedico) {
            if (medico.getCrm().equals(medicoVerificarCRM.getCrm())) {
                throw new RegraDeNegocioException("CRM já existe!");
            }
        }

        usuarioService.validarUsuarioEditado(usuarioCreateDTO, prestadorServicoEntity.getIdUsuario());
        usuarioService.editar(usuarioCreateDTO, prestadorServicoEntity.getIdUsuario());

        PrestadorServicoEntity medicoEditado = prestadorServicoRepository.save(prestadorServicoEntity);

        return getById(medicoEditado.getIdPrestadorServico());
    }

    @Transactional
    public void remover(Integer id) throws RegraDeNegocioException {
        PrestadorServicoEntity prestadorServicoEntity = getMedico(id);
        usuarioService.remover(prestadorServicoEntity.getIdUsuario());
        agendamentoService.removerPorMedicoDesativado(prestadorServicoEntity);
    }

    public void checarSeTemNumero(String string) throws RegraDeNegocioException {
        if (string.matches(".*[0-9].*")) { // checa se tem número no nome
            throw new RegraDeNegocioException("O nome da especialidade não pode conter número");
        }
    }


}
