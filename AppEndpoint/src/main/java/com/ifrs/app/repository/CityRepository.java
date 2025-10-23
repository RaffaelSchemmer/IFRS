package com.ifrs.app.repository;

import com.ifrs.app.model.City;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CityRepository {

    // Configuração do MariaDB
    private final String url = "jdbc:mariadb://localhost:3306/weatherdb"; // Altere host, porta e database
    private final String username = "seu_usuario";
    private final String password = "sua_senha";

    // Método para buscar todas as cidades
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM CITY";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                City city = new City();
                city.setId(rs.getLong("id"));
                city.setName(rs.getString("name"));
                city.setCountry(rs.getString("country"));
                city.setLat(rs.getDouble("lat"));
                city.setLon(rs.getDouble("lon"));
                cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    // Método para buscar cidade por ID
    public Optional<City> findById(Long id) {
        String sql = "SELECT * FROM CITY WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                City city = new City();
                city.setId(rs.getLong("id"));
                city.setName(rs.getString("name"));
                city.setCountry(rs.getString("country"));
                city.setLat(rs.getDouble("lat"));
                city.setLon(rs.getDouble("lon"));
                return Optional.of(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // Método para salvar ou atualizar cidade
    public void save(City city) {
        if (city.getId() == null || city.getId() == 0) {
            // Inserção
            String sql = "INSERT INTO CITY (name, country, lat, lon) VALUES (?, ?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, city.getName());
                ps.setString(2, city.getCountry());
                ps.setDouble(3, city.getLat());
                ps.setDouble(4, city.getLon());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    city.setId(keys.getLong(1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Atualização
            String sql = "UPDATE CITY SET name = ?, country = ?, lat = ?, lon = ? WHERE id = ?";
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, city.getName());
                ps.setString(2, city.getCountry());
                ps.setDouble(3, city.getLat());
                ps.setDouble(4, city.getLon());
                ps.setLong(5, city.getId());
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para deletar cidade por ID
    public void deleteById(Long id) {
        String sql = "DELETE FROM CITY WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
