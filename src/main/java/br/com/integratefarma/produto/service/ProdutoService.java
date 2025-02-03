package br.com.integratefarma.produto.service;

import br.com.integratefarma.produto.dto.ProdutoDTO;
import br.com.integratefarma.produto.repository.ProdutoRepository;
import br.com.integratefarma.utils.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public PageDTO<ProdutoDTO> paginadoProdutos(Integer pagina, Integer tamanho) {
        Pageable solicitacaoPagina = PageRequest.of(pagina,tamanho);
        Page<ProdutoDTO> produto = produtoRepository.listarFull(solicitacaoPagina);
        List<ProdutoDTO> prestadorServicoCompletoDTOS = produto.getContent().stream()
                .toList();

        return new PageDTO<>(produto.getTotalElements(),
                produto.getTotalPages(),
                pagina,
                tamanho,
                prestadorServicoCompletoDTOS);
    }
}
