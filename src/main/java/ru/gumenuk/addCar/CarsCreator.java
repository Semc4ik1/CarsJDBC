package ru.gumenuk.addCar;

import ru.gumenuk.connection.CarDatabaseProvider;
import ru.gumenuk.connection.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Scanner;

public class CarsCreator implements CarsAdder {

    public CarsCreator(CarDatabaseProvider databaseProvider) {
    }


    @Override
    public void addCar() {
        try (Connection connection = CarDatabaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO cars ( model, color, year, manufacturer, license_plate) VALUES ( ?, ?, ?, ?, ?)")) {

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


}


