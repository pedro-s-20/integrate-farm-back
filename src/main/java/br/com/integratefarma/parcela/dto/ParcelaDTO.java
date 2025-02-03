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
    private Integer id;
    private LocalDateTime dataVenda;
    private Double total;
    private Double parcela;
    private String observacao;
    private Integer numeroParcelas;
    private Integer clienteId;
}