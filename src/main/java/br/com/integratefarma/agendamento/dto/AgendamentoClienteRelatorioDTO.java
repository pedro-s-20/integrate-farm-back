package br.com.integratefarma.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoClienteRelatorioDTO {

    private Integer idCliente;
    private Integer idUsuario;
    private String cadastroOrgaoRegulador;
    private Double taxaAbatimento;
    private String cpf;
    private String email;
    private String nome;
    private String nomeCargo;
    private String contatos;
    private String cep;
    private Integer numero;
    private List<AgendamentoDTO> agendamentoDTOList;

    public AgendamentoClienteRelatorioDTO(Integer idCliente, Integer idUsuario, String cadastroOrgaoRegulador, Double taxaAbatimento, String cpf, String email, String nome, String nomeCargo, String contatos, String cep, Integer numero) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.cadastroOrgaoRegulador = cadastroOrgaoRegulador;
        this.taxaAbatimento = taxaAbatimento;
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.nomeCargo = nomeCargo;
        this.contatos = contatos;
        this.cep = cep;
        this.numero = numero;
    }
}
