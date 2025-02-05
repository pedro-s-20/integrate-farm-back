package br.com.integratefarma.fornecedor_RMI.controller;

import br.com.integratefarma.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DocumentacaoFornecedor<Fornecedores> {

    @Operation(summary = "Listar Fornecedores", description = "Lista todos os Fornecedores")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Todos os Fornecedores foram listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping()
    ResponseEntity<List<Fornecedores>> list() throws RegraDeNegocioException;

    @Operation(summary = "Criar Fornecedores", description = "Cria um Fornecedores")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Fornecedor criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<Void> create(@RequestBody Fornecedores e) throws RegraDeNegocioException;


    @Operation(summary = "Atualizar Fornecedor", description = "Atualiza um Fornecedor passado por parâmetro")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping()
    ResponseEntity<Void> update(@RequestBody Fornecedores e) throws RegraDeNegocioException;

    @Operation(summary = "Deletar Fornecedor", description = "Detela um Fornecedor passado por parâmetro")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Fornecedor deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping()
    ResponseEntity<Void> delete(@RequestBody Fornecedores e) throws RegraDeNegocioException;
}
