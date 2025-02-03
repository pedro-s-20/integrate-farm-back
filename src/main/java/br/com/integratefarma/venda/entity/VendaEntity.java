package br.com.integratefarma.venda.entity;

import br.com.integratefarma.cliente.entity.ClienteEntity;
import br.com.integratefarma.itemvenda.entity.ItemVendaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "tb_vendas")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "total_venda")
    private Double totalVenda;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "qnt_parcelas")
    private Integer quantidadeParcelas;

    @Column(name = "cliente_id", insertable= false, updatable=false)
    private Integer clienteId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity clienteEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendaEntity", cascade = CascadeType.MERGE)
    private Set<ItemVendaEntity> itemVendaEntities;
}