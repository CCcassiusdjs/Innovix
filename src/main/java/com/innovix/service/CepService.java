package com.innovix.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CepService {

    private static final String VIA_CEP_URL = "https://brasilapi.com.br/api/cep/v2/{cep}";

    public String buscarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = UriComponentsBuilder.fromUriString(VIA_CEP_URL)
                                         .buildAndExpand(cep)
                                         .toUriString();
        return restTemplate.getForObject(uri, String.class);
    }
}
