package br.com.integratefarma.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private Long quantidadeEstoque;
    private Long fornecedorId;
}