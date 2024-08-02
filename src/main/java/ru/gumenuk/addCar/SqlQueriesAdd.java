package ru.gumenuk.addCar;


public enum SqlQueriesAdd {

    ADD_ALL_CARS("SELECT * FROM cars;");

    private final String query;

    public String getQuery() {
        return query;
    }

    SqlQueriesAdd(String query) {
        this.query = query;
    }
}




