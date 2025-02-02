package br.com.integratefarma.prestadorservico.controller;

import br.com.integratefarma.agendamento.dto.AgendamentoListaDTO;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoCompletoDTO;
import br.com.integratefarma.prestadorservico.dto.PrestadorServicoUpdateDTO;
import br.com.integratefarma.prestadorservico.service.PrestadorServicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Prestador de serviço")
@Slf4j
@Validated
@RequestMapping("/prestador-servico")
@RestController
@RequiredArgsConstructor
public class PrestadorServicoController implements DocumentacaoPrestadorServico<PrestadorServicoCompletoDTO> {

    private final PrestadorServicoService prestadorServicoService;


    @Override
    @GetMapping("/verificar-info")
    public ResponseEntity<PrestadorServicoCompletoDTO> recuperarCliente() throws RegraDeNegocioException {
        return new ResponseEntity<>(prestadorServicoService.recuperarMedico(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AgendamentoListaDTO> getClienteAgentamentos() throws RegraDeNegocioException {
        return new ResponseEntity<>(prestadorServicoService.getMedicoAgentamentos(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PrestadorServicoCompletoDTO> update(PrestadorServicoUpdateDTO medico) throws RegraDeNegocioException {
        PrestadorServicoCompletoDTO medicoAtualizado = prestadorServicoService.editar(medico);
        return new ResponseEntity<>(medicoAtualizado, HttpStatus.OK);
    }


}
