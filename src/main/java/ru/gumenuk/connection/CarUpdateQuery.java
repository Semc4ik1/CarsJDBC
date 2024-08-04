package ru.gumenuk.connection;

public enum CarUpdateQuery {

    MODEL("UPDATE cars SET name = ? WHERE id = ?"),
    COLOR("UPDATE cars SET color = ? WHERE id = ?"),
    YEAR("UPDATE cars SET year = ? WHERE id = ?"),
    MANUFACTURER("UPDATE cars SET manufacturer = ? WHERE id = ?"),
    LICENSE_PLATE("UPDATE cars SET license_plate = ? WHERE id = ?");

    private final String query;

    CarUpdateQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
