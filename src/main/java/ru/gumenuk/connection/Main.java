package ru.gumenuk.connection;

import ru.gumenuk.addCar.CarsCreator;
import ru.gumenuk.addCar.CarsUpdate;
import ru.gumenuk.deleteCar.CarsDelete;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarDatabaseProvider dbProvider = new CarDatabaseProvider();

        CarsDelete carsDeleter = new CarsDelete(dbProvider);
        int carIdToDelete = 1;
        carsDeleter.removeCar(carIdToDelete);

        List<Car> carAfterDeletion = dbProvider.selectAll();
        System.out.println("\nСписок всех автомобилей после удаления:");
        for (Car car : carAfterDeletion) {
            System.out.println(car);
        }

        CarsCreator carsCreator = new CarsCreator(dbProvider);
        carsCreator.addCar();

        List<Car> carAfterAdding = dbProvider.selectAll();
        System.out.println("\nСписок всех автомобилей после добавления:");
        for (Car car : carAfterAdding) {
            System.out.println(car);
        }

        CarsUpdate carsUpdater = new CarsUpdate();
        carsUpdater.updateCar();

        List<Car> carAfterUpdating = dbProvider.selectAll();
        System.out.println("\nСписок всех автомобилей после обновления:");
        for (Car car : carAfterUpdating) {
            System.out.println(car);
        }
    }

}



