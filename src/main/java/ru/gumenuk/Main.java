package ru.gumenuk;

import ru.gumenuk.addCar.CarsCreator;
import ru.gumenuk.addCar.CarsDelete;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarDatabaseProvider dbProvider = new CarDatabaseProvider();

        CarsDelete carsDeleter = new CarsDelete(dbProvider);

        int carIdToDelete = 1;
        carsDeleter.removeCar(carIdToDelete);

        List<Cars> carsAfterDeletion = dbProvider.selectAll();
        System.out.println("\nСписок всех автомобилей после удаления:");
        for (Cars car : carsAfterDeletion) {
            System.out.println(car);
        }
        CarsCreator carsCreator = new CarsCreator(dbProvider);
        carsCreator.addCar(53, "Corolla", "Red", 2021, "ABC123", "ZA3AB0000H23456987");

        List<Cars> carsAfterAdding = dbProvider.selectAll();
        System.out.println("\nСписок всех автомобилей после добавления:");
        for (Cars car : carsAfterAdding) {
            System.out.println(car);
        }

    }
}


