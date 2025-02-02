package br.com.integratefarma.cliente.entity;

import br.com.integratefarma.parcela.entity.ParcelaEntity;
import br.com.integratefarma.venda.entity.VendaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "tb_clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "rg")
    private String rg;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "celular")
    private String celular;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clienteEntity", cascade = CascadeType.MERGE)
    private Set<ParcelaEntity> parcelaEntities;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clienteEntity", cascade = CascadeType.MERGE)
    private Set<VendaEntity> vendaEntities;
}