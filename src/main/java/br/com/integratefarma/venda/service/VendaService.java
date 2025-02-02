package br.com.integratefarma.venda.service;

import br.com.integratefarma.venda.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;

}
