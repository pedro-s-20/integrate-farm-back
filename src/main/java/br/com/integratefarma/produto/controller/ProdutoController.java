package br.com.integratefarma.produto.controller;

import br.com.integratefarma.produto.dto.ProdutoDTO;
import br.com.integratefarma.produto.service.ProdutoService;
import br.com.integratefarma.utils.dto.PageDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Produto")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/produto")
public class ProdutoController implements DocumentacaoProduto<ProdutoDTO> {

    private final ProdutoService produtoService;

    @Override
    public ResponseEntity<PageDTO<ProdutoDTO>> paginadoProdutos(Integer pagina, Integer tamanho) {
        return new ResponseEntity<>(produtoService.paginadoProdutos(pagina,tamanho), HttpStatus.OK);
    }
}
