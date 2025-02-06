package br.com.integratefarma.cliente.entity;

import br.com.integratefarma.agendamento.entity.AgendamentoEntity;
import br.com.integratefarma.parcela.entity.ParcelaEntity;
import br.com.integratefarma.usuario.entity.UsuarioEntity;
import br.com.integratefarma.venda.entity.VendaEntity;
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
@Table(name = "tb_clientes")
@Entity
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_usuario", insertable= false, updatable=false)
    private Integer idUsuario;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clienteEntity", cascade = CascadeType.MERGE)
    private Set<ParcelaEntity> parcelaEntities;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clienteEntity", cascade = CascadeType.MERGE)
    private Set<VendaEntity> vendaEntities;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuarioEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clienteEntity", cascade = CascadeType.ALL)
    private Set<AgendamentoEntity> agendamentoEntities;
}