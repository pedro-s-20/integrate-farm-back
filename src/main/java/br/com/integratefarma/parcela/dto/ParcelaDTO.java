package br.com.integratefarma.parcela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelaDTO {
    private Long id;
    private LocalDateTime dataVenda;
    private BigDecimal total;
    private BigDecimal parcela;
    private String observacao;
    private Long numeroParcelas;
    private Long clienteId;
}