package br.com.integratefarma.itemvenda.controller;

import br.com.integratefarma.itemvenda.dto.ItemVendaDTO;
import br.com.integratefarma.itemvenda.service.ItemVendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Item venda")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/item-venda")
public class ItemVendaController implements DocumentacaoItemVenda<ItemVendaDTO> {

    private final ItemVendaService itemVendaService;

}
