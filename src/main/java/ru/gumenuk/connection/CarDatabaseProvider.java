package ru.gumenuk.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public void addCar() {
        try (Connection connection = CarDatabaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     SqlQueries.INSERT_CAR.getQuery())) {

            Car car = collectCarDetails();

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

    private Car collectCarDetails() {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();

        String[] questions = {
                "Введите модель автомобиля:",
                "Введите цвет автомобиля:",
                "Введите год выпуска автомобиля:",
                "Введите производителя автомобиля:",
                "Введите номер автомобиля:"
        };

        String[] inputs = new String[5];

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            if (i == 2) {
                car.setYear(scanner.nextInt());
            } else {
                inputs[i] = scanner.next();
            }
        }

        car.setModel(inputs[0]);
        car.setColor(inputs[1]);
        car.setManufacturer(inputs[3]);
        car.setLicensePlate(inputs[4]);

        return car;
    }
    public void updateCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID автомобиля для изменения: ");
        int carId = scanner.nextInt();


        try (Connection connection = CarDatabaseProvider.getConnection()) {

            String checkQuery = "SELECT * FROM cars WHERE id = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setInt(1, carId);
                ResultSet resultSet = checkStatement.executeQuery();

                if (!resultSet.next()) {
                    System.out.println("Автомобиль с таким ID не найден.");
                    return;
                }
            }

            System.out.println("Какое поле вы хотите изменить?");
            System.out.println("1. Модель автомобиля");
            System.out.println("2. Цвет автомобиля");
            System.out.println("3. Год выпуска автомобиля");
            System.out.println("4. Производитель автомобиля");
            System.out.println("5. Номерной знак автомобиля");
            System.out.print("Введите номер действия (1-5): ");
            int choice = scanner.nextInt();


            String updateQuery ;

            switch (choice) {
                case 1:
                    System.out.print("Введите новую модель автомобиля: ");
                    String newName = scanner.nextLine();
                    updateQuery = "UPDATE cars SET name = ? WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, newName);
                        statement.setInt(2, carId);
                        statement.executeUpdate();
                        System.out.println("Модель автомобиля обновлена успешно!");
                    }
                    break;
                case 2:
                    System.out.print("Введите новый цвет автомобиля: ");
                    String newColor = scanner.nextLine();
                    updateQuery = "UPDATE cars SET color = ? WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, newColor);
                        statement.setInt(2, carId);
                        statement.executeUpdate();
                        System.out.println("Цвет автомобиля обновлён успешно!");
                    }
                    break;
                case 3:
                    System.out.print("Введите новый год выпуска автомобиля: ");
                    int newYear = scanner.nextInt();
                    updateQuery = "UPDATE cars SET year = ? WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setInt(1, newYear);
                        statement.setInt(2, carId);
                        statement.executeUpdate();
                        System.out.println("Год выпуска автомобиля обновлён успешно!");
                    }
                    break;
                case 4:
                    System.out.print("Введите нового производителя автомобиля: ");
                    String newManufacturer = scanner.nextLine();
                    updateQuery = "UPDATE cars SET manufacturer = ? WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, newManufacturer);
                        statement.setInt(2, carId);
                        statement.executeUpdate();
                        System.out.println("Производитель автомобиля обновлён успешно!");
                    }
                    break;
                case 5:
                    System.out.print("Введите новый номерной знак автомобиля: ");
                    String newLicensePlate = scanner.nextLine();
                    updateQuery = "UPDATE cars SET license_plate = ? WHERE id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, newLicensePlate);
                        statement.setInt(2, carId);
                        statement.executeUpdate();
                        System.out.println("Номерной знак автомобиля обновлён успешно!");
                    }
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении автомобиля: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}