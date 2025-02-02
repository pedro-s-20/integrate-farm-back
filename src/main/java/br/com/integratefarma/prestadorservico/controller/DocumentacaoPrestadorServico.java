package br.com.integratefarma.prestadorservico.controller;

import br.com.integratefarma.agendamento.dto.AgendamentoListaDTO;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface DocumentacaoPrestadorServico<PrestadorServicoCompletoDTO> {

    @Operation(summary = "Atualizar Prestador de Servico", description = "Recuperar as informações de PrestadorServico pelo respectivo token")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Prestador de Servico recuperado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<PrestadorServicoCompletoDTO> recuperarCliente() throws RegraDeNegocioException;

    @Operation(summary = "Recuperar os Agendamentos do Prestador de Servico", description = "Lista os Agendamentos de acordo com o Prestador de Servico logado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Os Agendamentos foram lsitados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/agendamentos")
    ResponseEntity<AgendamentoListaDTO> getClienteAgentamentos() throws RegraDeNegocioException;

    @Operation(summary = "Atualizar Prestador de Servico", description = "Atualiza um Prestador Servico passando o id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Prestador de Servico atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping()
    ResponseEntity<PrestadorServicoCompletoDTO> update(PrestadorServicoUpdateDTO prestadorServico) throws RegraDeNegocioException;
}
