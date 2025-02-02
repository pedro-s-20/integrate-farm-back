package br.com.integratefarma.venda.controller;

import br.com.integratefarma.venda.dto.VendaDTO;
import br.com.integratefarma.venda.service.VendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Venda")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/venda")
public class VendaController implements DocumentacaoVenda<VendaDTO> {

    private final VendaService vendaService;

}
