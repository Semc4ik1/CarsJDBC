package ru.gumenuk.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDatabaseProvider {
    private static final String URL = "jdbc:postgresql://localhost:5432/Cars";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "twerk228";

    public List<Car> selectAll() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_ALL_CARS.getQuery());
            ResultSet resultSet = statement.executeQuery();

            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car();

                car.setId(resultSet.getInt(1));
                car.setModel(resultSet.getString(2));
                car.setColor(resultSet.getString(3));
                car.setYear(resultSet.getInt(4));
                car.setManufacturer(resultSet.getString(5));
                car.setLicensePlate(resultSet.getString(6));
                cars.add(car);

            }

            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}