package ru.gumenuk.addCar;

import ru.gumenuk.connection.CarDatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CarsUpdate {

    public void updateCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID автомобиля для изменения: ");
        scanner.nextLine();

        try (Connection connection = CarDatabaseProvider.getConnection()) {
            System.out.println("Какое поле вы хотите изменить?");
            System.out.println("1. Модель автомобиля");
            System.out.println("2. Цвет автомобиля");
            System.out.println("3. Год выпуска автомобиля");
            System.out.println("4. Производитель автомобиля");
            System.out.println("5. Номерной знак автомобиля");
            System.out.print("Введите номер действия (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String updateQuery = "";
            switch (choice) {
                case 1:
                    System.out.print("Введите новую модель автомобиля: ");
                    String newName = scanner.nextLine();
                    updateQuery = "UPDATE cars SET name = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, newName);
                        statement.executeUpdate();
                        System.out.println("Название автомобиля обновлено успешно!");
                    }
                    break;
                case 2:
                    System.out.print("Введите новый цвет автомобиля: ");
                    String newBrand = scanner.nextLine();
                    updateQuery = "UPDATE cars SET color = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, newBrand);
                        statement.executeUpdate();
                        System.out.println("Марка автомобиля обновлена успешно!");
                    }
                    break;
                case 3:
                    System.out.print("Введите год выпуска автомобиля: ");
                    double newYear = scanner.nextDouble();
                    updateQuery = "UPDATE cars SET year = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setDouble(1, newYear);
                        statement.executeUpdate();
                        System.out.println("Цена автомобиля обновлена успешно!");
                    }
                    break;
                case 4:
                    System.out.print("Введите производителя автомобиля: ");
                    double newManufacture = scanner.nextDouble();
                    updateQuery = "UPDATE cars SET year = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setDouble(1, newManufacture);
                        statement.executeUpdate();
                        System.out.println("Цена автомобиля обновлена успешно!");
                    }
                    break;
                case 5:
                    System.out.print("Введите номерной знак автомобиля: ");
                    double newGetLicensePlate = scanner.nextDouble();
                    updateQuery = "UPDATE cars SET year = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setDouble(1, newGetLicensePlate);
                        statement.executeUpdate();
                        System.out.println("Цена автомобиля обновлена успешно!");
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
}