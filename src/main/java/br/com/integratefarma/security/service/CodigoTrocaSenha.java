package br.com.integratefarma.security.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CodigoTrocaSenha {
    private static Map<String, Integer> tokenBD;

    public  Map<String, Integer> getTokenBD() {
        return tokenBD;
    }

    public  void setTokenBD(Map<String, Integer> tokenBD) {
        CodigoTrocaSenha.tokenBD = tokenBD;
    }


}
