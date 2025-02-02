package br.com.integratefarma.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {

    private Integer idAgendamento;
    private Integer idCliente;
    private Integer idPrestadorServico;
    private String tratamento;
    private String exame;
    private LocalDateTime dataHorario;
    private Double valorAgendamento;
}
