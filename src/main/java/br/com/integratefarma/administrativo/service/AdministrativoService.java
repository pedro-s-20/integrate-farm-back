package br.com.integratefarma.administrativo.service;

import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.cliente.repository.ClienteRepository;
import br.com.integratefarma.cliente.service.ClienteService;
import br.com.integratefarma.email.enums.TipoEmail;
import br.com.integratefarma.email.service.EmailService;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.prestadorservico.repository.PrestadorServicoRepository;
import br.com.integratefarma.prestadorservico.service.PrestadorServicoService;
import br.com.integratefarma.usuario.dto.UsuarioCreateDTO;
import br.com.integratefarma.usuario.dto.UsuarioDTO;
import br.com.integratefarma.usuario.dto.UsuarioUpdateDTO;
import br.com.integratefarma.usuario.entity.UsuarioEntity;
import br.com.integratefarma.usuario.repository.UsuarioRepository;
import br.com.integratefarma.usuario.service.UsuarioService;
import br.com.integratefarma.utils.dto.PageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdministrativoService {

    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final ClienteService clienteService;
    private final PrestadorServicoService prestadorServicoService;
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final ClienteRepository clienteRepository;

    public UsuarioDTO reativarUsuario(Integer idUsuario) throws RegraDeNegocioException {
        return usuarioService.reativarUsuario(idUsuario);
    }

    public List<UsuarioDTO> listar(){
        List<UsuarioDTO> usuarioDTOS = usuarioRepository.findAll()
                .stream()
                .filter(usuarioEntity -> usuarioEntity.getIdCargo().equals(1))
                .filter(usuario -> usuario.getAtivo().equals(1))
                .map(adm -> objectMapper.convertValue(adm, UsuarioDTO.class))
                .collect(Collectors.toList());
        System.out.println();

        return usuarioDTOS;
    }

    public UsuarioDTO adicionar(UsuarioCreateDTO usuario) throws RegraDeNegocioException{

        UsuarioEntity usuarioEntity = objectMapper.convertValue(usuario, UsuarioEntity.class);
        usuarioEntity.setIdCargo(1);

        usuarioService.validarUsuarioAdicionado(usuarioEntity);
        usuarioService.adicionar(usuarioEntity);

        usuarioRepository.save(usuarioEntity);

        try{
            emailService.sendEmailUsuario(usuarioEntity, TipoEmail.USUARIO_CADASTRO, null);
        } catch (MessagingException | TemplateException | IOException e) {
            usuarioService.hardDelete(usuarioEntity.getIdUsuario());
            throw new RegraDeNegocioException("Erro ao enviar o e-mail. Cadastro não realizado.");
        }

        return usuarioService.getById(usuarioEntity.getIdUsuario());
    }

    public UsuarioDTO editar(Integer id, UsuarioUpdateDTO usuario) throws RegraDeNegocioException {

        UsuarioEntity usuarioEntity = getAdm(id);
        if (!usuarioEntity.getIdCargo().equals(1)) {
            throw new RegraDeNegocioException("Este usuário não é um administrador.");
        }

        UsuarioCreateDTO usuarioCreateDTO = objectMapper.convertValue(usuario, UsuarioCreateDTO.class);

        usuarioService.validarUsuarioEditado(usuarioCreateDTO, usuarioEntity.getIdUsuario());
        usuarioService.editar(usuarioCreateDTO, usuarioEntity.getIdUsuario());

        UsuarioEntity usuarioEditado = usuarioRepository.save(usuarioEntity);

        return usuarioService.getById(usuarioEditado.getIdUsuario());
    }

    public void remover(Integer id) throws RegraDeNegocioException {
        usuarioService.remover(getAdm(id).getIdUsuario());
    }

    public UsuarioEntity getAdm(Integer id) throws RegraDeNegocioException {
        return usuarioRepository.findById(id)
                .filter(usuario -> usuario.getAtivo().equals(1))
                .orElseThrow(() -> new RegraDeNegocioException("Administrador não existe!"));
    }

    public ClienteCompletoDTO getClienteById(Integer id) throws RegraDeNegocioException {
        return clienteService.getById(id);
    }

    public PrestadorServicoCompletoDTO getMedicoById(Integer id) throws RegraDeNegocioException {
        return prestadorServicoService.getById(id);
    }

    public void removerMedico(Integer id) throws RegraDeNegocioException {
        prestadorServicoService.remover(id);
    }

    public void removerCliente(Integer id) throws RegraDeNegocioException {
        clienteService.remover(id);
    }

    public PageDTO<PrestadorServicoCompletoDTO> listMedico(Integer pagina, Integer tamanho){
        Pageable solicitacaoPagina = PageRequest.of(pagina,tamanho);
        Page<PrestadorServicoCompletoDTO> medico = prestadorServicoRepository.listarFull(solicitacaoPagina);
        List<PrestadorServicoCompletoDTO> prestadorServicoCompletoDTOS = medico.getContent().stream()
                .toList();

        return new PageDTO<>(medico.getTotalElements(),
                medico.getTotalPages(),
                pagina,
                tamanho,
                prestadorServicoCompletoDTOS);
    }

    public PageDTO<ClienteCompletoDTO> listCliente(Integer pagina, Integer tamanho) {
        Pageable solicitacaoPagina = PageRequest.of(pagina,tamanho);
        Page<ClienteCompletoDTO> cliente = clienteRepository.listarFull(solicitacaoPagina);
        List<ClienteCompletoDTO> clienteCompletoDTOS = cliente.getContent().stream()
                .toList();

        return new PageDTO<>(cliente.getTotalElements(),
                cliente.getTotalPages(),
                pagina,
                tamanho,
                clienteCompletoDTOS);
    }
}
