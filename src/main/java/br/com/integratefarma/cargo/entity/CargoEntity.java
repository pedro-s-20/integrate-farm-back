package br.com.integratefarma.cargo.entity;

import br.com.integratefarma.usuario.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "CARGO")
@Entity
public class CargoEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo", insertable= false, updatable=false)
    private Integer idCargo;
    @Column(name = "nome", insertable= false, updatable=false)
    private String nomeCargo;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargoEntity", cascade = CascadeType.MERGE)
    private Set<UsuarioEntity> usuarioEntities;

    @Override
    public String getAuthority() {
        return nomeCargo;
    }
}
