package ru.gumenuk.connection;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarDatabaseProvider dbProvider = new CarDatabaseProvider();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                           \nВыберите действие:
                    1 - Добавить автомобиль
                    2 - Изменить автомобиль
                    3 - Удалить автомобиль
                    4 - Показать все автомобили
                    5 - Выход\s
                     Ваш выбор: \s
                          \s""");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> dbProvider.addCar();

                case 2 -> dbProvider.updateCar();
                case 3 -> {
                    System.out.print("Введите ID автомобиля для удаления: ");
                    int carIdToDelete = scanner.nextInt();
                    dbProvider.removeCar(carIdToDelete);
                }
                case 4 -> {
                    List<Car> allCars = dbProvider.selectAll();
                    System.out.println("\nСписок всех автомобилей:");
                    for (Car car : allCars) {
                        System.out.println(car);
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
    }
}





