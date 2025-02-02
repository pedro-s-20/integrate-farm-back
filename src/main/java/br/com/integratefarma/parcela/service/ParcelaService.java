package br.com.integratefarma.parcela.service;

import br.com.integratefarma.parcela.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;

}
