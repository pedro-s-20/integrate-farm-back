package br.com.integratefarma.fornecedor.controller;

import br.com.integratefarma.fornecedor.dto.FornecedorDTO;
import br.com.integratefarma.fornecedor.service.FornecedorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Fornecedor")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/fornecedor")
public class FornecedorController implements DocumentacaoFornecedor<FornecedorDTO> {

    private final FornecedorService fornecedorService;

}
