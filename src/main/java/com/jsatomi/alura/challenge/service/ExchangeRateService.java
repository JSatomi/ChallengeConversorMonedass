package com.jsatomi.alura.challenge.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {
    private String url_str = "https://v6.exchangerate-api.com/v6/7d2ffdebb6a1023188a89a6a/latest/USD";
    private JsonObject rates;

    public ExchangeRateService() throws IOException, InterruptedException {
        // Making Request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url_str))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        //Convertir de Archivo JSON a OBJETO
        JsonObject object = JsonParser.parseString(response.body()).getAsJsonObject();
        //Le pido los valores que contiene la key "conversion_rates"
        this.rates = object.getAsJsonObject("conversion_rates");
    }

    public double getRate(String tasa){
        return this.rates.get(tasa).getAsDouble();

    }
}
