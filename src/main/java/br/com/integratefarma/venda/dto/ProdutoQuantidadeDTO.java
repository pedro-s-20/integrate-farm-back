package br.com.integratefarma.venda.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoQuantidadeDTO {
    @NotNull
    @Schema(description = "Id do produto", example = "1", required = true)
    private Integer idProduto;
    @NotNull
    @Schema(description = "Quantidade de unidade do produto", example = "3", required = true)
    private Integer quantidade;
    @NotNull
    @DecimalMax("99999.0")
    @DecimalMin("0.0")
    @Schema(description = "Subtotal do produto (quantidade * valor da unidade)", example = "27.50", required = true)
    private Double subtotal;
}
