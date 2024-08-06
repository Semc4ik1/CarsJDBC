package ru.gumenuk.connection;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarDatabaseProvider dbProvider = new CarDatabaseProvider();
        CarDetailsCollector collector = new CarDetailsCollector();
        try (Scanner scanner = new Scanner(System.in)) {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("""
                               \nВыберите действие:
                        1 - Добавить автомобиль
                        2 - Обновить автомобиль
                        3 - Удалить автомобиль
                        4 - Показать все автомобили
                        5 - Выход
                         Ваш выбор:
                            \s""");


                int choice =Integer.parseInt(scanner.nextLine());


                switch (choice) {

                    case 1 -> {
                        Car car = collector.collectNewCar(scanner);
                        dbProvider.addCar(car);
                    }
                    case 2 -> {
                        System.out.println("Введите ID автомобиля для обновления");
                        int id = Integer.parseInt(scanner.nextLine());
                        Car existing = dbProvider.selectById(id);
                        Car car = collector.collectExistingCar(scanner, existing);
                        dbProvider.updateCar(id, car);
                    }
                    case 3 -> {
                        System.out.print("Введите ID автомобиля для удаления: ");
                        int carIdToDelete = Integer.parseInt(scanner.nextLine());
                        dbProvider.removeCar(carIdToDelete);
                    }
                    case 4 -> {
                        List<Car> allCars = dbProvider.selectAll();
                        System.out.println("\nСписок всех автомобилей:");
                        for (Car par : allCars) {
                            System.out.println(par);
                        }
                    }
                    case 5 -> {
                        System.out.println("Выход из программы.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



