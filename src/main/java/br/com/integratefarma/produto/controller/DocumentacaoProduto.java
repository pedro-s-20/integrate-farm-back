package br.com.integratefarma.produto.controller;

import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.utils.dto.PageDTO;
import br.com.sistema.model.Produtos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface DocumentacaoProduto<ProdutoDTO> {
    @Operation(summary = "Lista todos os produtos", description = "Lista todos os produtos de forma paginada")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Os prestadores de servico foram listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/paginado-produtos/")
    ResponseEntity<PageDTO<ProdutoDTO>> paginadoProdutos(Integer pagina, Integer tamanho);


    @Operation(summary = "Criar Produto", description = "Cria um Produto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<Void> create(@RequestBody Produtos e) throws RegraDeNegocioException;

    @Operation(summary = "Atualizar Produto", description = "Atualiza um Produto passado por parâmetro")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping()
    ResponseEntity<Void> update(@RequestBody Produtos e) throws RegraDeNegocioException;

    @Operation(summary = "Deletar Produto", description = "Detela um Produto passado por parâmetro")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping()
    ResponseEntity<Void> delete(@RequestBody Produtos e) throws RegraDeNegocioException;
}
