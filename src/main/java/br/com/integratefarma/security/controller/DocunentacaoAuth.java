package br.com.integratefarma.security.controller;

import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.cliente.dto.ClienteCreateDTO;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCreateDTO;
import br.com.integratefarma.security.dto.LoginDTO;
import br.com.integratefarma.security.dto.RedefinicaoSenhaDTO;
import br.com.integratefarma.security.dto.TrocaSenhaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface DocunentacaoAuth {
    @Operation(summary = "Autenticar", description = "Ao fazer login gera um token para se autenticar")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Token gerado com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    String auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException;

    @Operation(summary = "Adicionar Cliente", description = "Cria um novo cadastro de cliente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro concluido com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastro-cliente")
    ClienteCompletoDTO adicionarCliente(@RequestBody @Valid ClienteCreateDTO cliente) throws RegraDeNegocioException;

    @Operation(summary = "Adiciona Prestador de Servico", description = "Cria um novo cadastro de prestador de serviço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro concluido com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastro-prestador-servico")
    PrestadorServicoCompletoDTO adicionarMedico(@RequestBody @Valid PrestadorServicoCreateDTO prestadorServico) throws RegraDeNegocioException;

    @Operation(summary = "Trocar senha logado", description = "Permite a troca de senha logado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Troca de senha feita com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/alterar-senha-logado")
    ResponseEntity<Void> trocarSenha(@RequestBody @Valid TrocaSenhaDTO trocaSenhaDTO) throws RegraDeNegocioException;

    @Operation(summary = "Solicitar Troca de Senha", description = "Envia um email com um codigo que permite a troca de senha")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Solicitação feita com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/esqueci-minha-senha")
    ResponseEntity<Void> solicitarRedefinicao(@RequestParam(name="email") @NotNull String email) throws RegraDeNegocioException;

    @Operation(summary = "Redefinir Senha", description = "Redefini a senha do usuario a partir de um codigo enviado para seu email")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Senha trocada com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/redefinir-senha")
    ResponseEntity<Void> redefinir(@RequestBody @Valid RedefinicaoSenhaDTO redefinicaoSenhaDTO) throws RegraDeNegocioException;
}
