package com.eduardocruzdev.Service;

import com.eduardocruzdev.Models.ExchangeRate;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeService {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c1642080af33dc88b993f9cd/pair/";

    public static ExchangeRate exchangeRate(String baseCurrency, String targetCurrency) {
        String url = API_URL + baseCurrency + "/" + targetCurrency;
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ExchangeRate.class);
        } catch (Exception e) {
            throw new RuntimeException("Error consiguiendo la tasa de cambio: " + e.getMessage(), e);
        }
    }

    public static double convertAmount(String baseCurrency, String targetCurrency, double amount) {
        return amount * exchangeRate(baseCurrency, targetCurrency).getConversion_rate();
    }
}
