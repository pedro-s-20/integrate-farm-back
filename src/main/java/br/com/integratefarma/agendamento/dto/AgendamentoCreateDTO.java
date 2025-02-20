package br.com.integratefarma.agendamento.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoCreateDTO {

    @NotNull
    @Schema(description = "Id do cliente", example = "1", required = true)
    private Integer idCliente;
    @NotNull
    @Schema(description = "Id do prestador de serviço", example = "2", required = true)
    private Integer idPrestadorServico;
    @Size(max = 40)
    @Schema(description = "Tratamento a ser seguido pelo cliente", example = "Dipirona de 6 em 6 horas")
    private String tratamento;
    @Size(max = 40)
    @Schema(description = "Exame(s) pedidos pelo prestador de serviço", example = "Sangue e urina")
    private String exame;
    @NotNull
    @Schema(description = "Data e horário da consulta", example = "25/08/2023 15:00", required = true)
    private LocalDateTime dataHorario;
    @NotNull
    @Size(max = 40)
    @Schema(description = "Status do agendamento", example = "PENDENTE, IMPEDIMENTO, CONFIRMADO ou FINALIZADO", required = true)
    private String status;
}
