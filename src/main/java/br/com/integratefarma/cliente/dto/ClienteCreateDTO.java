package br.com.integratefarma.cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDTO {

    @NotBlank
    @Size(min = 11, max = 11)
    @Schema(description = "CPF", example = "12345678911", required = true)
    private String cpf;
    @NotBlank
    @Size(min = 5, max = 30)
    @Schema(description = "RG", example = "MG-123456", required = true)
    private String rg;
    @NotBlank
    @Size(max = 300)
    @Schema(description = "Email", example = "fulano.silva@gmail.com", required = true)
    private String email;
    @NotBlank
    @Size(max = 255)
    @Schema(description = "Nome", example = "Fulano da Silva", required = true)
    private String nome;
    @NotBlank
    @Size(max = 300)
    @Schema(description = "Senha", example = "123senha123", required = true)
    private String senha;
    @NotBlank
    @Size(max = 8)
    @Schema(description = "Endereço", example = "Rua Exemplo", required = true)
    private String endereco;
    @NotNull
    @Min(value = 1)
    @Max(value = 999999999)
    @Schema(description = "Número do endereço", example = "15", required = true)
    private Integer numero;
    @NotBlank
    @Size(max = 8)
    @Schema(description = "Complemento", example = "Ap 123", required = true)
    private String complemento;
    @NotBlank
    @Size(max = 8)
    @Schema(description = "Bairro", example = "Bela Vista", required = true)
    private String bairro;
    @NotBlank
    @Size(max = 8)
    @Schema(description = "Cidade", example = "Xique-Xique", required = true)
    private String cidade;
    @NotBlank
    @Size(max = 8)
    @Schema(description = "Estado", example = "BA", required = true)
    private String estado;
    @NotBlank
    @Size(max = 8)
    @Schema(description = "CEP", example = "12345678", required = true)
    private String cep;
    @NotBlank
    @Schema(description = "Número de telefone ou celular", example = "849261850, 4599765234")
    private String contatos;
}
