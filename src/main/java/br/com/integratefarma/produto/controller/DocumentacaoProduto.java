package br.com.integratefarma.produto.controller;

import br.com.integratefarma.utils.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

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

}
