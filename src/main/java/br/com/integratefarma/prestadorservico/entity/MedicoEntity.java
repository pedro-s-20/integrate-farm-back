package br.com.integratefarma.prestadorservico.entity;

import br.com.integratefarma.agendamento.entity.AgendamentoEntity;
import br.com.integratefarma.especialidade.entity.EspecialidadeEntity;
import br.com.integratefarma.usuario.entity.UsuarioEntity;
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
@Entity(name = "Medico")
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;

    @Column(name = "id_usuario", insertable = false, updatable=false)
    private Integer idUsuario;
    @Column(name = "id_especialidade", insertable = false, updatable = false)
    private Integer idEspecialidade;
    @Column(name = "crm")
    private String crm;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
    private EspecialidadeEntity especialidadeEntity;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuarioEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medicoEntity", cascade = CascadeType.ALL)
    private Set<AgendamentoEntity> agendamentoEntities;

}
