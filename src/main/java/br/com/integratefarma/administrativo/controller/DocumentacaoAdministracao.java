package br.com.integratefarma.administrativo.controller;

import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.usuario.dto.UsuarioCreateDTO;
import br.com.integratefarma.usuario.dto.UsuarioDTO;
import br.com.integratefarma.usuario.dto.UsuarioUpdateDTO;
import br.com.integratefarma.utils.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DocumentacaoAdministracao {

    @Operation(summary = "Reativa um Usuário", description = "Reativa um usuário passando seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O usuário foi reativado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}/reativação")
    ResponseEntity<UsuarioDTO> reativarUsuario(@PathVariable("idUsuario") Integer idUsuario) throws RegraDeNegocioException;


    @Operation(summary = "Recuperar um Cliente", description = "Recupera um Cliente passando seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O Cliente foi recuperado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/cliente/{id}")
    ResponseEntity<ClienteCompletoDTO> getByIdCliente(Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Recuperar um Prestador de Serviço", description = "Recupera um Prestador de Serviço passando seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O Prestador de Serviço foi recuperado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/prestador-servico/{id}")
    ResponseEntity<PrestadorServicoCompletoDTO> getByIdPrestadorServico(Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Lista todos os administradores", description = "Lista todos os administradores")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Os Administradores foram listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-administradores")
    ResponseEntity<List<UsuarioDTO>> list() throws RegraDeNegocioException;

    @Operation(summary = "Desativa um Prestador de Serviço", description = "Desativa um Prestador de Serviço passando seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O Prestador de Serviço foi desativado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/desativar-prestador-servico/{id}")
    ResponseEntity<Void> deletePrestadorServico(Integer id) throws RegraDeNegocioException;


    @Operation(summary = "Desativa um Cliente", description = "Desativa um Cliente passando seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O Cliente foi desativado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/desativar-cliente/{id}")
    ResponseEntity<Void> deleteCliente(Integer id) throws RegraDeNegocioException;


    @Operation(summary = "Cria um Administrador", description = "Cria um Administrador")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O Administrador foi criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/criar-admin")
    ResponseEntity<UsuarioDTO> create(UsuarioCreateDTO usuario) throws RegraDeNegocioException;


    @Operation(summary = "Atualiza um Administrador", description = "Atualiza um Administrador pssando seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O Administrador foi atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/editar-admin/{id}")
    ResponseEntity<UsuarioDTO> update(Integer id, UsuarioUpdateDTO admin) throws RegraDeNegocioException;


    @Operation(summary = "Desativa um Administrador", description = "Desativa um Administrador pssando seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "O Desativado foi atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/desativar-admin/{id}")
    ResponseEntity<Void> remove(Integer id) throws RegraDeNegocioException;


    @Operation(summary = "Lista todos os prestadores de servico", description = "Lista todos os prestadores de servico de forma paginada")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Os prestadores de servico foram listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/paginado-prestador-servico/")
    ResponseEntity<PageDTO<PrestadorServicoCompletoDTO>> paginadoPrestadorServico(Integer pagina, Integer tamanho);


    @Operation(summary = "Lista todos os Clientes", description = "Lista todos os Clientes de forma paginada")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Os Clientes foram listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/paginado-cliente/")
    ResponseEntity<PageDTO<ClienteCompletoDTO>> paginadoCliente(Integer pagina, Integer tamanho);
}