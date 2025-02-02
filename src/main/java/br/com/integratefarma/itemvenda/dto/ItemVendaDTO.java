package br.com.integratefarma.itemvenda.dto;

import lombok.*;

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