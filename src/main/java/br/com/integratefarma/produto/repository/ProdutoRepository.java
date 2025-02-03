package br.com.integratefarma.produto.repository;

import br.com.integratefarma.produto.dto.ProdutoDTO;
import br.com.integratefarma.produto.entity.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query("select new br.com.integratefarma.produto.dto.ProdutoDTO(" +
            " p.id, " +
            " p.descricao, " +
            " p.preco, " +
            " p.linkImagem, " +
            " p.quantidadeEstoque) " +
            " from tb_produtos p"
    )
    Page<ProdutoDTO> listarFull(Pageable solicitacaoPagina);
}
