package br.com.integratefarma.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {
    private Long id;
    private LocalDateTime dataVenda;
    private BigDecimal totalVenda;
    private String observacoes;
    private Long clienteId;
}