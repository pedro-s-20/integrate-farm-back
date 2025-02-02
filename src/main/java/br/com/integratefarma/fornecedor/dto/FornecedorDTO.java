package br.com.integratefarma.fornecedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String celular;
    private String cep;
    private String endereco;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
