package br.com.integratefarma.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoListaDTO {

    private List<AgendamentoDTO> agendamentoDTOList;

}
