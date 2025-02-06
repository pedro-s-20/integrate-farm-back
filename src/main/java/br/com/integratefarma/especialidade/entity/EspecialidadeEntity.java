package br.com.integratefarma.especialidade.entity;

import br.com.integratefarma.prestadorservico.entity.PrestadorServicoEntity;
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
@Table(name = "ESPECIALIDADE")
@Entity
public class EspecialidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    private Integer idEspecialidade;
    @Column(name = "valor")
    private double valor;
    @Column(name = "nome")
    private String nomeEspecialidade;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "especialidadeEntity", cascade = CascadeType.MERGE)
    private Set<PrestadorServicoEntity> prestadorServicoEntities;

}
