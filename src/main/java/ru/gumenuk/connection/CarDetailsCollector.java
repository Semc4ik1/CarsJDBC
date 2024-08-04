package ru.gumenuk.connection;

import java.util.Scanner;

public class CarDetailsCollector {
    protected Car collectCarDetails() {
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
