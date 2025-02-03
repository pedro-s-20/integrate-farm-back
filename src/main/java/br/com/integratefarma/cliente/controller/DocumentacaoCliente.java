package br.com.integratefarma.cliente.controller;

import br.com.integratefarma.agendamento.dto.AgendamentoListaDTO;
import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.cliente.dto.ClienteUpdateDTO;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.venda.dto.VendaCreateDTO;
import br.com.integratefarma.venda.dto.VendaDTO;
import br.com.integratefarma.venda.dto.VendaListaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface DocumentacaoCliente<ClienteDTO> {

    @Operation(summary = "Recuperar Cliente", description = "Recuperar as informações de Cliente pelo respectivo token")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente recuperado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/verificar-info")
    ResponseEntity<ClienteCompletoDTO> recuperarCliente() throws RegraDeNegocioException;

    @Operation(summary = "Recuperar os Agendamentos do Cliente", description = "Lista os Agendamentos de acordo com o Cliente logado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Os Agendamentos foram lsitados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/agendamentos")
    ResponseEntity<AgendamentoListaDTO> getClienteAgentamentos() throws RegraDeNegocioException;

    @Operation(summary = "Listar Compras do Cliente", description = "Lista todas as compras do cliente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Todas as compras foram listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/compras")
    ResponseEntity<VendaListaDTO> getClienteCompras() throws RegraDeNegocioException;

    @Operation(summary = "Criar Venda", description = "Cria uma Venda (de uma compra feita pelo cliente)")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Venda criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/realizar-compra")
    ResponseEntity<VendaDTO> createClienteVenda(@Valid @RequestBody VendaCreateDTO input) throws RegraDeNegocioException;

    @Operation(summary = "Atualizar Cliente", description = "Atualiza um Cliente passando o id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping()
    ResponseEntity<ClienteCompletoDTO> update(ClienteUpdateDTO cliente) throws RegraDeNegocioException;

}
