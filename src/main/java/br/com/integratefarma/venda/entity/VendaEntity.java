package br.com.integratefarma.venda.entity;

import br.com.integratefarma.cliente.entity.ClienteEntity;
import br.com.integratefarma.itemvenda.entity.ItemVendaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_vendas")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "total_venda")
    private BigDecimal totalVenda;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "cliente_id", insertable= false, updatable=false)
    private Long clienteId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private ClienteEntity clienteEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendaEntity", cascade = CascadeType.MERGE)
    private Set<ItemVendaEntity> itemVendaEntities;
}