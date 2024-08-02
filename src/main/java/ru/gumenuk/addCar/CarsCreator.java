package ru.gumenuk.addCar;

import ru.gumenuk.CarDatabaseProvider;
import ru.gumenuk.Cars;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.List;

public class CarsCreator implements CarsAdder {
    private CarDatabaseProvider databaseProvider;

    public CarsCreator(CarDatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }


    @Override
    public void addCar(int id, String model, String color, int year, String manufacturer, String licensePlate) {
        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO cars (id, model, color, year, manufacturer, license_plate) VALUES (?, ?, ?, ?, ?, ?)")) {

            Cars car = new Cars();

            car.setId(id);
            car.setModel(model);
            car.setColor(color);
            car.setYear(year);
            car.setManufacturer(manufacturer);
            car.setLicensePlate(licensePlate);

            statement.setInt(1, car.getId());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getColor());
            statement.setInt(4, car.getYear());
            statement.setString(5, car.getManufacturer());
            statement.setString(6, car.getLicensePlate());

            int rowsAffected = statement.executeUpdate();


            if (rowsAffected > 0) {
                System.out.println("A new car has been added successfully!");
            }
            List<Cars> carsAfterAdding = databaseProvider.selectAll();
            /*System.out.println("\nСписок всех автомобилей после добавления:");
            for (Cars currentCar : carsAfterAdding) {
                System.out.println(currentCar);
            }*/
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}


