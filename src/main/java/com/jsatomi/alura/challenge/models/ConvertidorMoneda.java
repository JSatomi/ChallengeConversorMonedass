package com.jsatomi.alura.challenge.models;

import com.jsatomi.alura.challenge.service.ExchangeRateService;

import java.text.DecimalFormat;

public class ConvertidorMoneda {

    private ExchangeRateService exService;
    private DecimalFormat df = new DecimalFormat("#.##");

    public ConvertidorMoneda(ExchangeRateService exService) {
        this.exService = exService;
    }

    public String convertir(String monedaOrigen, String monedaDestino, double cantidad){
        double tasaOrigen = monedaOrigen.equals("USD")? 1: exService.getRate(monedaOrigen);
        double tasaDestino = monedaDestino.equals("USD")? 1: exService.getRate(monedaDestino);
        double resultado = (cantidad/tasaOrigen)*tasaDestino;
        return df.format(resultado);
    }
}
