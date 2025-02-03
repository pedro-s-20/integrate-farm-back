package br.com.integratefarma.funcionario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String senha;
    private String cargo;
    private String nivelAcesso;
    private String telefone;
    private String celular;
    private String cep;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
