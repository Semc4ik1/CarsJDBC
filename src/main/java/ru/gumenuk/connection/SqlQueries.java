package ru.gumenuk.connection;

public enum SqlQueries {

    SELECT_ALL_CARS("SELECT * FROM cars;"),
    INSERT_CAR("INSERT INTO cars (model, color, year, manufacturer, license_plate) VALUES (?, ?, ?, ?, ?);"),
    DELETE_CAR("DELETE FROM cars WHERE id = ?;");

    private final String query;

    SqlQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}