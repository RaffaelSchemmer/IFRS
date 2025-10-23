package com.ifrs.app.controller;

import com.ifrs.app.model.City;
import com.ifrs.app.repository.CityRepository;
import com.ifrs.app.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

// Controlador REST que recebe requisições HTTP
@RestController
public class WeatherController {

    private final CityRepository cityRepository; // Repositório de cidades
    private final WeatherService weatherService; // Serviço que chama a API
    private final String validApiKey = "app123"; // ApiKey que o app deve fornecer

    public WeatherController(CityRepository cityRepository, WeatherService weatherService) {
        this.cityRepository = cityRepository;
        this.weatherService = weatherService;
    }

    @GetMapping("/weather") // Endpoint GET /weather?apikey=...
    public String getWeather(@RequestParam("apikey") String apiKey) {
        // Valida ApiKey enviada pelo app
        if (!validApiKey.equals(apiKey)) {
            return "{\"error\":\"ApiKey inválida\"}";
        }

        // Busca todas as cidades cadastradas
        List<City> cities = cityRepository.findAll();
        if (cities.isEmpty()) {
            return "{\"error\":\"Nenhuma cidade cadastrada\"}";
        }

        // Escolhe uma cidade aleatória
        Random random = new Random();
        City randomCity = cities.get(random.nextInt(cities.size()));

        // Retorna JSON da OpenWeatherMap
        return weatherService.getWeather(randomCity, "fbd2f9ebe81059a86e30de49d62d6b7d");
    }
}
