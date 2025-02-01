package br.com.integratefarma.cliente.controller;

import br.com.integratefarma.cliente.dto.ClienteDTO;
import br.com.integratefarma.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

}
