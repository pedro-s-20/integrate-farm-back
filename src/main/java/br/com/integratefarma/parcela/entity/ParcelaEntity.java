package br.com.integratefarma.parcela.entity;

import br.com.integratefarma.cliente.entity.ClienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tb_parcelas")
@Entity
public class ParcelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "total")
    private Double total;

    @Column(name = "parcela")
    private Double parcela;

    @Column(name = "obs")
    private String observacao;

    @Column(name = "num_parcelas")
    private Integer numeroParcelas;

    @Column(name = "cliente_id", insertable= false, updatable=false)
    private Integer clienteId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity clienteEntity;
}