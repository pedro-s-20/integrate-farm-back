package br.com.integratefarma.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {
    private Integer id;
    private LocalDateTime dataVenda;
    private Double totalVenda;
    private String observacoes;
    private Integer clienteId;
    private Integer quantidadeParcelas;
    private List<ProdutosVendaOutputDTO> produtos;
}