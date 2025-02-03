package br.com.integratefarma.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaListaDTO {
    private List<VendaDTO> vendaDTOList;
}
