package br.com.integratefarma.cliente.service;

import br.com.integratefarma.agendamento.dto.AgendamentoClienteRelatorioDTO;
import br.com.integratefarma.agendamento.dto.AgendamentoListaDTO;
import br.com.integratefarma.agendamento.service.AgendamentoService;
import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.cliente.dto.ClienteCreateDTO;
import br.com.integratefarma.cliente.dto.ClienteUpdateDTO;
import br.com.integratefarma.cliente.entity.ClienteEntity;
import br.com.integratefarma.cliente.repository.ClienteRepository;
import br.com.integratefarma.email.enums.TipoEmail;
import br.com.integratefarma.email.service.EmailService;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.usuario.dto.UsuarioCreateDTO;
import br.com.integratefarma.usuario.entity.UsuarioEntity;
import br.com.integratefarma.usuario.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
    private final AgendamentoService agendamentoService;
    private final EmailService emailService;

    public ClienteService(ClienteRepository clienteRepository, ObjectMapper objectMapper, UsuarioService usuarioService, @Lazy AgendamentoService agendamentoService, EmailService emailService) {
        this.clienteRepository = clienteRepository;
        this.objectMapper = objectMapper;
        this.usuarioService = usuarioService;
        this.agendamentoService = agendamentoService;
        this.emailService = emailService;
    }

    public ClienteCompletoDTO recuperarCliente() throws RegraDeNegocioException {
        ClienteEntity clienteEntity = clienteRepository.getClienteEntityByIdUsuario(usuarioService.getIdLoggedUser());
        return getById(clienteEntity.getId());
    }

    public ClienteCompletoDTO getById(Integer idCliente) throws RegraDeNegocioException {
        Optional<ClienteCompletoDTO> clienteRetornado = clienteRepository.getByIdPersonalizado(idCliente);
        if (clienteRetornado.isEmpty()) {
            throw new RegraDeNegocioException("Usuário não encontrado.");
        }
        return clienteRetornado.get();
    }

    public ClienteEntity getCliente(Integer id) throws RegraDeNegocioException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não existe!"));
    }

    public AgendamentoListaDTO getClienteAgentamentos() throws RegraDeNegocioException {
        ClienteCompletoDTO clienteCompletoDTO = recuperarCliente();
        AgendamentoClienteRelatorioDTO agendamentoClienteRelatorioDTO = agendamentoService.getRelatorioClienteById(clienteCompletoDTO.getIdCliente());

        return objectMapper.convertValue(agendamentoClienteRelatorioDTO, AgendamentoListaDTO.class);
    }

    public ClienteCompletoDTO adicionar(ClienteCreateDTO cliente) throws RegraDeNegocioException {
        checarSeTemNumero(cliente.getNome());

        // Adicionando o Usuario com as informações recebidas no ClienteCreateDTO
        UsuarioEntity usuarioEntity = objectMapper.convertValue(cliente, UsuarioEntity.class);
        usuarioEntity.setIdCargo(3);

        usuarioService.validarUsuarioAdicionado(usuarioEntity);
        usuarioService.adicionar(usuarioEntity);

        // Adicionando o usuario que foi salvado no Cliente a salvar
        ClienteEntity clienteEntity = objectMapper.convertValue(cliente, ClienteEntity.class);
        clienteEntity.setUsuarioEntity(usuarioEntity);
        String[] numeros = cliente.getContatos().split(",");
        if (numeros.length > 0) {
            if(numeros[0] != null) clienteEntity.setCelular(numeros[0]);
            if(numeros[1] != null) clienteEntity.setTelefone(numeros[1]);
        }

        clienteRepository.save(clienteEntity);
        try{
            emailService.sendEmailUsuario(clienteEntity.getUsuarioEntity(), TipoEmail.USUARIO_CADASTRO, null);
        } catch (MessagingException | TemplateException | IOException e) {
            usuarioService.hardDelete(clienteEntity.getUsuarioEntity().getIdUsuario());
            throw new RegraDeNegocioException("Erro ao enviar o e-mail. Cadastro não realizado.");
        }

        return getById(clienteEntity.getId());
    }

    public ClienteCompletoDTO editar(ClienteUpdateDTO cliente) throws RegraDeNegocioException {
        ClienteEntity clienteEntity = objectMapper.convertValue(recuperarCliente(), ClienteEntity.class);

        clienteEntity.setUsuarioEntity(usuarioService.getUsuario(clienteEntity.getIdUsuario()));

        checarSeTemNumero(cliente.getNome());

        UsuarioCreateDTO usuarioCreateDTO = objectMapper.convertValue(cliente, UsuarioCreateDTO.class);

        usuarioService.validarUsuarioEditado(usuarioCreateDTO, clienteEntity.getIdUsuario());
        usuarioService.editar(usuarioCreateDTO, clienteEntity.getIdUsuario());

        ClienteEntity clienteEditado = clienteRepository.save(clienteEntity);

        return getById(clienteEditado.getId());
    }

    @Transactional
    public void remover(Integer id) throws RegraDeNegocioException {
        ClienteEntity clienteEntity = getCliente(id);
        usuarioService.remover(clienteEntity.getIdUsuario());
        agendamentoService.removerPorClienteDesativado(clienteEntity);
    }

    public void checarSeTemNumero(String string) throws RegraDeNegocioException {
        if (string.matches(".*[0-9].*")) { // checa se tem número no nome
            throw new RegraDeNegocioException("O nome da especialidade não pode conter número");
        }
    }


}

