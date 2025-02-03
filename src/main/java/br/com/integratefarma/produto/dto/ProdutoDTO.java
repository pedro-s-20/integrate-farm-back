package br.com.integratefarma.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Integer id;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque;
    private Integer fornecedorId;
}