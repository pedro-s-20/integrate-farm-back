package br.com.integratefarma.parcela.entity;

import br.com.integratefarma.cliente.entity.ClienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_fornecedores")
public class ParcelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "parcela")
    private BigDecimal parcela;

    @Column(name = "obs")
    private String observacao;

    @Column(name = "num_parcelas")
    private Long numeroParcelas;

    @Column(name = "cliente_id", insertable= false, updatable=false)
    private Long clienteId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private ClienteEntity clienteEntity;
}