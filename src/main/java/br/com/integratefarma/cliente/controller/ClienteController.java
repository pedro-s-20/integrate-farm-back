package br.com.integratefarma.cliente.controller;

import br.com.integratefarma.agendamento.dto.AgendamentoListaDTO;
import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.cliente.dto.ClienteDTO;
import br.com.integratefarma.cliente.dto.ClienteUpdateDTO;
import br.com.integratefarma.cliente.service.ClienteService;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Cliente")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController implements DocumentacaoCliente<ClienteDTO> {

    private final ClienteService clienteService;

    @Override
    public ResponseEntity<ClienteCompletoDTO> recuperarCliente() throws RegraDeNegocioException {
        return new ResponseEntity<>(clienteService.recuperarCliente(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AgendamentoListaDTO> getClienteAgentamentos() throws RegraDeNegocioException {
        return new ResponseEntity<>(clienteService.getClienteAgentamentos(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteCompletoDTO> update(ClienteUpdateDTO cliente) throws RegraDeNegocioException {
        return new ResponseEntity<>(clienteService.editar(cliente), HttpStatus.OK);
    }

}
