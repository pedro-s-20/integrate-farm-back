package br.com.integratefarma.itemvenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaDTO {
    private Integer id;
    private Integer idVenda;
    private Integer idProduto;
    private Integer quantidade;
    private Double subtotal;
}