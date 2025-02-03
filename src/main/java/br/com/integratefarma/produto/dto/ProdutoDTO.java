package br.com.integratefarma.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Integer id;
    private String descricao;
    private Double preco;
    private String linkImagem;
    private Integer quantidadeEstoque;
}