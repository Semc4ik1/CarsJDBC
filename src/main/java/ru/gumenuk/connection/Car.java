package ru.gumenuk.connection;

public class Car {
    private int id;
    private String model;
    private String color;
    private int year;
    private String manufacturer;
    private String licensePlate;

    public Car(int id, String model, String color, int year, String manufacturer, String licensePlate) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.year = year;
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", manufacturer='" + manufacturer + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
