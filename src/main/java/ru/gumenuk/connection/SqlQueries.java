package ru.gumenuk.connection;


public enum SqlQueries {

    SELECT_ALL_CARS("SELECT * FROM cars;");

    private final String query;

    public String getQuery() {
        return query;
    }

    SqlQueries(String query) {
        this.query = query;
    }
}





