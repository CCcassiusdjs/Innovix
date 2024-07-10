package com.innovix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.innovix.service.CepService;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/api/cep/{cep}")
    public String buscarCep(@PathVariable String cep) {
        return cepService.buscarCep(cep);
    }
}

