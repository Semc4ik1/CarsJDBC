package ru.gumenuk.connection;

import java.util.Scanner;

public class CarDetailsCollector {
    public Car collectNewCar(Scanner scanner) {

        Car car = new Car();

        System.out.println("Введите модель автомобиля: ");
        car.setModel(scanner.nextLine());
        System.out.println("Введите цвет автомобиля: ");
        car.setColor(scanner.nextLine());
        System.out.println("Введите год выпуска автомобиля: ");
        car.setYear(Integer.parseInt(scanner.nextLine()));
        System.out.println("Введите производителя автомобиля: ");
        car.setManufacturer(scanner.nextLine());
        System.out.println("Введите номер автомобиля: ");
        car.setLicensePlate(scanner.nextLine());

        return car;
    }

    public Car collectExistingCar(Scanner scanner, Car car) {

        System.out.println("Введите модель автомобиля [текущее название: " + car.getModel() + "]:");
        String model = scanner.nextLine();
        if(!model.isBlank()) {
            car.setModel(model);
        }

        System.out.println("Введите цвет автомобиля [текущее название: " + car.getColor() + "]:");
        String color = scanner.nextLine();
        if(!color.isBlank()) {
            car.setColor(color);
        }

        System.out.println("Введите год выпуска автомобиля [текущее название: " + car.getYear() + "]:");
        String yearInput = scanner.nextLine();
        if(!yearInput.isBlank()) {
            car.setYear(Integer.parseInt(yearInput));
        }

        System.out.println("Введите производителя автомобиля [текущее название: " + car.getManufacturer() + "]:");
        String manufacturer = scanner.nextLine();
        if(!manufacturer.isBlank()) {
            car.setManufacturer(manufacturer);
        }

        System.out.println("Введите номер автомобиля [текущее название: " + car.getLicensePlate() + "]:");
        String licensePlate = scanner.nextLine();
        if(!licensePlate.isBlank()) {
            car.setLicensePlate(licensePlate);
        }

        return car;
    }

}
