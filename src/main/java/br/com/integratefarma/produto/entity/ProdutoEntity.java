package br.com.integratefarma.produto.entity;

import br.com.integratefarma.fornecedor.entity.FornecedorEntity;
import br.com.integratefarma.itemvenda.entity.ItemVendaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "qtd_estoque")
    private Long quantidadeEstoque;

    @Column(name = "for_id", insertable= false, updatable=false)
    private Long fornecedorId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "for_id", referencedColumnName = "for_id")
    private FornecedorEntity fornecedorEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produtoEntity", cascade = CascadeType.MERGE)
    private Set<ItemVendaEntity> itemVendaEntities;
}