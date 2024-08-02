package ru.gumenuk.addCar;

import ru.gumenuk.CarDatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarsDelete implements CarsRemove {
    private CarDatabaseProvider databaseProvider;

    public CarsDelete(CarDatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    @Override
    public void removeCar(int id) {
        try (Connection connection = databaseProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM cars WHERE id = ?")) {
             statement.setInt(1,id);
            int rowsAffected = statement.executeUpdate();

            System.out.println("Removed User :" + id);
            if (rowsAffected > 0) {
                System.out.println("A car was removed successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
