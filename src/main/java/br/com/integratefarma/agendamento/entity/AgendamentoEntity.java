package br.com.integratefarma.agendamento.entity;

import br.com.integratefarma.cliente.entity.ClienteEntity;
import br.com.integratefarma.prestadorservico.entity.MedicoEntity;
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
@Entity(name = "Agendamento")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Integer idAgendamento;
    @Column(name = "id_cliente", insertable = false, updatable = false)
    private Integer idCliente;
    @Column(name = "id_medico", insertable = false, updatable = false)
    private Integer idMedico;
    @Column(name = "tratamento")
    private String tratamento;
    @Column(name = "exame")
    private String exame;
    @Column(name = "data_horario")
    private LocalDateTime dataHorario;
    @Column(name = "valor")
    private Double valorAgendamento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private ClienteEntity clienteEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    private MedicoEntity medicoEntity;

}
