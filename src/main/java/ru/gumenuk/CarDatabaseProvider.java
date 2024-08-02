package ru.gumenuk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDatabaseProvider {
    private static final String URL = "jdbc:postgresql://localhost:5432/Cars";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "twerk228";

    public List<Cars> selectAll() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_ALL_CARS.getQuery());
            ResultSet resultSet = statement.executeQuery();

            List<Cars> cars = new ArrayList<>();
            while (resultSet.next()) {
                Cars car = new Cars();

                car.setId(resultSet.getInt(1));
                car.setModel(resultSet.getString(2));
                car.setColor(resultSet.getString(3));
                car.setYear(resultSet.getInt(4));
                car.setManufacturer(resultSet.getString(5));
                car.setLicensePlate(resultSet.getString(6));
                cars.add(car);

            }
           /* for (Cars car : cars) {
                System.out.println("Начальный список автомобилей");
                System.out.println(car);
            }*/
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}