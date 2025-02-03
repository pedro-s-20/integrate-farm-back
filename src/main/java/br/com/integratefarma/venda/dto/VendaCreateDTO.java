package br.com.integratefarma.venda.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaCreateDTO {
    @NotNull
    @Schema(description = "Data e horário da consulta", example = "25/08/2023 15:00", required = true)
    private LocalDateTime dataVenda;
    @NotNull
    @DecimalMax("99999.0")
    @DecimalMin("0.0")
    @Schema(description = "Total do valor da venda.", example = "17.80", required = true)
    private Double totalVenda;
    @Size(max = 255)
    @Schema(description = "Observações da venda.", example = "Enviar somenta após 15h.")
    private String observacoes;
    @NotNull
    @Schema(description = "Quantidade de parcelas da venda", example = "1", required = true)
    private Integer quantidadeParcelas;
    @Size(max = 255)
    @Schema(description = "Observações da parcela.", example = "Emitir boleto com nome de outro pagador.")
    private String observacoesParcela;
    @Schema(description = "Produtos contidos na venda.")
    private List<ProdutoQuantidadeDTO> produtos;
}
