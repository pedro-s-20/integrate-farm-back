package br.com.integratefarma.produto.service;

import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.produto.dto.ProdutoDTO;
import br.com.integratefarma.produto.repository.ProdutoRepository;
import br.com.integratefarma.utils.dto.PageDTO;
import br.com.sistema.model.Produtos;
import br.com.sistema.rmi.ProdutosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Value("${rmi.host}")
    private String RMI_HOST;

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

    public void salvarProdutoDao(Produtos input) throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            ProdutosService service = (ProdutosService) registry.lookup("ProdutosService");
            service.SalvarProdutoDao(input);
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            throw new RegraDeNegocioException("Erro ao salvar o produto: " + e.getMessage());
        }
    }

    public void editarProdutoDao(Produtos input) throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            ProdutosService service = (ProdutosService) registry.lookup("ProdutosService");
            service.EditarProdutoDao(input);
        } catch (Exception e) {
            throw new RegraDeNegocioException("Erro ao editar o produto: " + e.getMessage());
        }
    }

    public void excluirProdutoDao(Produtos input) throws RegraDeNegocioException {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry(RMI_HOST, 1099);

            // Procurar o serviço remoto
            ProdutosService service = (ProdutosService) registry.lookup("ProdutosService");
            service.ExcluirProdutoDao(input);
        } catch (Exception e) {
            throw new RegraDeNegocioException("Erro ao excluir o produto: " + e.getMessage());
        }
    }
}
