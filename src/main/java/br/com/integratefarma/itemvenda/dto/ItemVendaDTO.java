package br.com.integratefarma.itemvenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaDTO {
    private Long id;
    private Long idVenda;
    private Long idProduto;
    private Long quantidade;
    private BigDecimal subtotal;
}