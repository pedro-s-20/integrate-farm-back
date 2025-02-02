package br.com.integratefarma.security.controller;

import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.cliente.dto.ClienteCreateDTO;
import br.com.integratefarma.cliente.service.ClienteService;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCreateDTO;
import br.com.integratefarma.prestadorservico.service.PrestadorServicoService;
import br.com.integratefarma.security.dto.LoginDTO;
import br.com.integratefarma.security.dto.RedefinicaoSenhaDTO;
import br.com.integratefarma.security.dto.TrocaSenhaDTO;
import br.com.integratefarma.security.service.TokenService;
import br.com.integratefarma.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(name="Autenticação")
@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController implements DocunentacaoAuth {
    private final TokenService tokenService;
    private final ClienteService clienteService;
    private final PrestadorServicoService prestadorServicoService;
    private final UsuarioService usuarioService;

    @Override
    public String auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        return tokenService.autenticar(loginDTO);
    }
    @Override
    public ClienteCompletoDTO adicionarCliente(@RequestBody @Valid ClienteCreateDTO cliente) throws RegraDeNegocioException {
        return clienteService.adicionar(cliente);
    }
    @Override
    public PrestadorServicoCompletoDTO adicionarMedico(@RequestBody @Valid PrestadorServicoCreateDTO medico) throws RegraDeNegocioException {
        return prestadorServicoService.adicionar(medico);
    }

    @Override
    public ResponseEntity<Void> trocarSenha(@RequestBody @Valid TrocaSenhaDTO trocaSenhaDTO) throws RegraDeNegocioException {
        usuarioService.trocarSenha(trocaSenhaDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> solicitarRedefinicao(@RequestParam(name="email") @NotNull String email) throws RegraDeNegocioException {
        usuarioService.solicitarRedefinirSenha(email);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> redefinir(@RequestBody @Valid RedefinicaoSenhaDTO redefinicaoSenhaDTO) throws RegraDeNegocioException {
        usuarioService.redefinirSenha(redefinicaoSenhaDTO);
        return ResponseEntity.ok().build();
    }

}
