package ru.gumenuk.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDatabaseProvider {
    private static final String URL = "jdbc:postgresql://localhost:5432/Cars";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "twerk228";

    public List<Car> selectAll() {
        try (Connection connection = CarDatabaseProvider.getConnection()) {
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

    public void removeCar(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_CAR.getQuery())) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            System.out.println("Removed Car: " + id);
            if (rowsAffected > 0) {
                System.out.println("A car was removed successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCar(Car car) {
        try (Connection connection = CarDatabaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     SqlQueries.INSERT_CAR.getQuery())) {

            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setInt(3, car.getYear());
            statement.setString(4, car.getManufacturer());
            statement.setString(5, car.getLicensePlate());

            int rowsAffected = statement.executeUpdate();


            if (rowsAffected > 0) {
                System.out.println("A new car has been added successfully!");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void updateCar(int id, Car car)  {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     SqlQueries.UPDATE_BY_ID.getQuery())) {

            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setInt(3, car.getYear());
            statement.setString(4, car.getManufacturer());
            statement.setString(5, car.getLicensePlate());
            statement.setInt(6, id);

            int affected = statement.executeUpdate();
            if (affected > 0) {
                System.out.println("The update was successful!");
            }
            System.out.println("Отлично, машина с id номер " + id + " обновлено");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Car selectById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_BY_ID.getQuery())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new IllegalArgumentException("Cannot find car with id = " + id);
            }

            Car car = new Car();

            car.setId(resultSet.getInt(1));
            car.setModel(resultSet.getString(2));
            car.setColor(resultSet.getString(3));
            car.setYear(resultSet.getInt(4));
            car.setManufacturer(resultSet.getString(5));
            car.setLicensePlate(resultSet.getString(6));


            return car;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
