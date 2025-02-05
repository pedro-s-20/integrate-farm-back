package br.com.integratefarma.fornecedor_RMI.controller;

import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.fornecedor_RMI.service.FornecedorService;
import br.com.sistema.model.Fornecedores;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Fornecedor")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/fornecedor")
public class FornecedorController implements DocumentacaoFornecedor<Fornecedores> {

    private final FornecedorService fornecedorService;

    @Override
    public ResponseEntity<List<Fornecedores>> list() throws RegraDeNegocioException {
        return new ResponseEntity<>(fornecedorService.listarFornecedoreDao(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> create(Fornecedores e) throws RegraDeNegocioException {
        fornecedorService.salvarFornecedoresDao(e);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> update(Fornecedores e) throws RegraDeNegocioException {
        fornecedorService.editarFornecedoreDao(e);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> delete(Fornecedores e) throws RegraDeNegocioException {
        fornecedorService.ecluirFornecedoreDao(e);
        return ResponseEntity.ok().build();
    }
}
