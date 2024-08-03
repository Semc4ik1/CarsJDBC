package ru.gumenuk.connection;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarDatabaseProvider dbProvider = new CarDatabaseProvider();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить автомобиль");
            System.out.println("2 - Изменить автомобиль");
            System.out.println("3 - Удалить автомобиль");
            System.out.println("4 - Показать все автомобили");
            System.out.println("5 - Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    dbProvider.addCar();
                    break;
                case 2:
                    dbProvider.updateCar();
                case 3:
                    System.out.print("Введите ID автомобиля для удаления: ");
                    int carIdToDelete = scanner.nextInt();
                    dbProvider.removeCar(carIdToDelete);
                    break;
                case 4:
                    List<Car> allCars = dbProvider.selectAll();
                    System.out.println("\nСписок всех автомобилей:");
                    for (Car car : allCars) {
                        System.out.println(car);
                    }
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

}



