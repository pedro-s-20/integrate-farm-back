package br.com.integratefarma.produto.entity;

import br.com.integratefarma.fornecedor.entity.FornecedorEntity;
import br.com.integratefarma.itemvenda.entity.ItemVendaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "tb_produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "qtd_estoque")
    private Integer quantidadeEstoque;

    @Column(name = "link_imagem")
    private String linkImagem;

    @Column(name = "for_id", insertable= false, updatable=false)
    private Integer fornecedorId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "for_id", referencedColumnName = "id")
    private FornecedorEntity fornecedorEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produtoEntity", cascade = CascadeType.MERGE)
    private Set<ItemVendaEntity> itemVendaEntities;
}