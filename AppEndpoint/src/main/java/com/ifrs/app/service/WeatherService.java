package com.ifrs.app.service;

import com.ifrs.app.model.City;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

// Serviço que faz a consulta da API do OpenWeatherMap
@Service
public class WeatherService {

    private final OkHttpClient client = new OkHttpClient(); // Cliente HTTP para fazer GET

    public String getWeather(City city, String apiKey) {
        // Monta a URL com latitude, longitude e API key
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric&lang=pt_br",
                city.getLat(), city.getLon(), apiKey);

        Request request = new Request.Builder()
                .url(url)
                .build();

        // Executa a requisição de forma síncrona
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Erro na API: " + response.code());
            }

            if (response.body() == null) {
                throw new RuntimeException("Resposta vazia");
            }

            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao conectar com a API", e);
        }
    }
}
