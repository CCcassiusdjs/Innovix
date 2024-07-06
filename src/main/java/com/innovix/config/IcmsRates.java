package com.innovix.config;

import java.util.HashMap;
import java.util.Map;

public class IcmsRates {

    private static final Map<String, Double> rates;

    static {
        rates = new HashMap<>();
        rates.put("Acre", 0.19);
        rates.put("Alagoas", 0.19);
        rates.put("Amapá", 0.18);
        rates.put("Amazonas", 0.20);
        rates.put("Bahia", 0.205);
        rates.put("Ceará", 0.20);
        rates.put("Distrito Federal", 0.20);
        rates.put("Espírito Santo", 0.17);
        rates.put("Goiás", 0.19);
        rates.put("Maranhão", 0.22);
        rates.put("Minas Gerais", 0.18);
        rates.put("Mato Grosso do Sul", 0.17);
        rates.put("Mato Grosso", 0.17);
        rates.put("Pará", 0.19);
        rates.put("Pernambuco", 0.205);
        rates.put("Piauí", 0.21);
        rates.put("Paraná", 0.195);
        rates.put("Rio de Janeiro", 0.20);
        rates.put("Rio Grande do Norte", 0.18);
        rates.put("Rio Grande do Sul", 0.17);
        rates.put("Rondônia", 0.195);
        rates.put("Roraima", 0.20);
        rates.put("Santa Catarina", 0.17);
        rates.put("Sergipe", 0.19);
        rates.put("São Paulo", 0.18);
        rates.put("Tocantins", 0.20);
    }

    public static double getRate(String state) {
        return rates.getOrDefault(state, 0.0);
    }
}