package br.com.integratefarma.funcionario.controller;

import br.com.integratefarma.funcionario.dto.FuncionarioDTO;
import br.com.integratefarma.funcionario.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Funcionario")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/funcionario")
public class FuncionarioController implements DocumentacaoFuncionario<FuncionarioDTO> {

    private final FuncionarioService funcionarioService;

}
