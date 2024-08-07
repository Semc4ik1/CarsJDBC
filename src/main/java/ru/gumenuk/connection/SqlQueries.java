package ru.gumenuk.connection;

public enum SqlQueries {

    SELECT_ALL_CARS("SELECT * FROM cars;"),
    INSERT_CAR("INSERT INTO cars (model, color, year, manufacturer, license_plate) VALUES (?, ?, ?, ?, ?);"),
    UPDATE_BY_ID("UPDATE cars SET model = ?, color = ?, year = ?, manufacturer = ?, license_plate = ? WHERE id = ?;"),
    DELETE_CAR("DELETE FROM cars WHERE id = ?;"),
    SELECT_BY_ID("SELECT * FROM cars WHERE id = ?;");




    private final String query;

    SqlQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}