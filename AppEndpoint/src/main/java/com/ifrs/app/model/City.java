package com.ifrs.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Representa a tabela CITY no banco de dados H2
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária auto-increment
    private Long id;

    private String name;    // Nome da cidade
    private String country; // País da cidade
    private Double lat;     // Latitude
    private Double lon;     // Longitude

    // Construtor padrão (necessário para JPA)
    public City() {}

    // Construtor com parâmetros
    public City(String name, String country, Double lat, Double lon) {
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
