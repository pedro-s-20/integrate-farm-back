package br.com.integratefarma.parcela.controller;

import br.com.integratefarma.parcela.dto.ParcelaDTO;
import br.com.integratefarma.parcela.service.ParcelaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Parcela")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/parcela")
public class ParcelaController implements DocumentacaoParcela<ParcelaDTO> {

    private final ParcelaService parcelaService;

}
