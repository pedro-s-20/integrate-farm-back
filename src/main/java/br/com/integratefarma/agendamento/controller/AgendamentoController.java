package br.com.integratefarma.agendamento.controller;

import br.com.integratefarma.agendamento.dto.AgendamentoClienteRelatorioDTO;
import br.com.integratefarma.agendamento.dto.AgendamentoCreateDTO;
import br.com.integratefarma.agendamento.dto.AgendamentoDTO;
import br.com.integratefarma.agendamento.dto.AgendamentoPrestadorServicoRelatorioDTO;
import br.com.integratefarma.agendamento.service.AgendamentoService;
import br.com.integratefarma.utils.dto.PageDTO;
import br.com.integratefarma.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Agendamento")
@Validated
@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController implements DocumentacaoAgendamento<AgendamentoDTO, AgendamentoCreateDTO, Integer, Integer> {

    private final AgendamentoService agendamentoService;

    @Override
    public ResponseEntity<PageDTO<AgendamentoDTO>> list(Integer pagina, Integer tamanho) {
        return new ResponseEntity<>(agendamentoService.findAllPaginado(pagina, tamanho), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AgendamentoDTO> getById(Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(agendamentoService.getById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AgendamentoClienteRelatorioDTO> getClienteByIdPersonalizado(@PathVariable("idCliente") Integer idCliente) throws RegraDeNegocioException {
        return new ResponseEntity<>(agendamentoService.getRelatorioClienteById(idCliente), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AgendamentoPrestadorServicoRelatorioDTO> getPrestadorServicoByIdPersonalizado(@PathVariable("idPrestadorServico") Integer idPrestadorServico) throws RegraDeNegocioException {
        return new ResponseEntity<>(agendamentoService.getRelatorioPrestadorServicoById(idPrestadorServico), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AgendamentoDTO> create(AgendamentoCreateDTO agendamento) throws RegraDeNegocioException {
        return new ResponseEntity<>(agendamentoService.adicionar(agendamento), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AgendamentoDTO> update(Integer id, AgendamentoCreateDTO agendamento) throws RegraDeNegocioException {
        return new ResponseEntity<>(agendamentoService.editar(id, agendamento), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) throws RegraDeNegocioException {
        agendamentoService.remover(id);
        return ResponseEntity.ok().build();
    }



}
