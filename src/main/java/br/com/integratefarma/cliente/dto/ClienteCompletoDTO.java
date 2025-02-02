package br.com.integratefarma.cliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCompletoDTO {

    private Integer idCliente;
    private Integer idUsuario;
    private String cpf;
    private String email;
    private String nome;
    private String nomeCargo;
    private String contatos;
    @JsonIgnore
    private String cep;
    private Integer numero;

}
