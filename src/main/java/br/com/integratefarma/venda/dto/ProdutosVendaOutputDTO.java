package br.com.integratefarma.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosVendaOutputDTO {
    private Integer idProduto;
    private String descricaoProduto;
    private Integer quantidade;
}
