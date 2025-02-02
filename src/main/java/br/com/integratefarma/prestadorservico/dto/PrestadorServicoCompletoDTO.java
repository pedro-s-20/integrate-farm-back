package br.com.integratefarma.prestadorservico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestadorServicoCompletoDTO {

    private Integer idPrestadorServico;
    private String crm;
    private Integer idEspecialidade;
    private Integer idUsuario;
    private Double valor;
    private String nomeEspecilidade;
    private String cpf;
    private String email;
    private String nome;
    private String nomeCargo;
    private String contatos;
    private String cep;
    private Integer numero;

}
