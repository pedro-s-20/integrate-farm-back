package br.com.integratefarma.itemvenda.entity;

import br.com.integratefarma.produto.entity.ProdutoEntity;
import br.com.integratefarma.venda.entity.VendaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "tb_itensvendas")
public class ItemVendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "venda_id", insertable= false, updatable=false)
    private Long idVenda;

    @Column(name = "produto_id", insertable= false, updatable=false)
    private Long idProduto;

    @Column(name = "qtd")
    private Long quantidade;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "venda_id", referencedColumnName = "id")
    private VendaEntity vendaEntity;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoEntity produtoEntity;
}