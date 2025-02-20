package br.com.integratefarma.administrativo.controller;

import br.com.integratefarma.administrativo.service.AdministrativoService;
import br.com.integratefarma.cliente.dto.ClienteCompletoDTO;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.usuario.dto.UsuarioCreateDTO;
import br.com.integratefarma.usuario.dto.UsuarioDTO;
import br.com.integratefarma.usuario.dto.UsuarioUpdateDTO;
import br.com.integratefarma.utils.dto.PageDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Administrativo")
@Validated
@RestController
@RequestMapping("/administrativo")
@RequiredArgsConstructor
public class AdministrativoController implements DocumentacaoAdministracao {
    private final AdministrativoService administrativoService;

    @Override
    public ResponseEntity<UsuarioDTO> reativarUsuario (@PathVariable ("idUsuario") Integer idUsuario) throws RegraDeNegocioException {
        return new ResponseEntity<>(administrativoService.reativarUsuario(idUsuario), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteCompletoDTO> getByIdCliente(Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(administrativoService.getClienteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PrestadorServicoCompletoDTO> getByIdPrestadorServico(Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(administrativoService.getPrestadorServicoById(id), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<UsuarioDTO>> list() throws RegraDeNegocioException{
        return new ResponseEntity<>(administrativoService.listar(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePrestadorServico(Integer id) throws RegraDeNegocioException {
        administrativoService.removerPrestadorServico(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Void> deleteCliente(Integer id) throws RegraDeNegocioException {
        administrativoService.removerCliente(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UsuarioDTO> create(UsuarioCreateDTO usuario) throws RegraDeNegocioException {
        return new ResponseEntity<>(administrativoService.adicionar(usuario), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioDTO> update(Integer id, UsuarioUpdateDTO admin) throws RegraDeNegocioException {
        UsuarioDTO usuarioAtualizado = administrativoService.editar(id,admin);
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> remove(Integer id) throws RegraDeNegocioException {
        administrativoService.remover(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<PageDTO<PrestadorServicoCompletoDTO>> paginadoPrestadorServico(Integer pagina, Integer tamanho){
        return new ResponseEntity<>(administrativoService.listPrestadorServico(pagina,tamanho),HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PageDTO<ClienteCompletoDTO>> paginadoCliente(Integer pagina, Integer tamanho){
        return new ResponseEntity<>(administrativoService.listCliente(pagina,tamanho),HttpStatus.OK);
    }
}
