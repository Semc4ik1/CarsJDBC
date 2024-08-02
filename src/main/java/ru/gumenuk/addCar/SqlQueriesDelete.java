package ru.gumenuk.addCar;


public enum SqlQueriesDelete {


    DELETE_ALL_CARS("SELECT * FROM cars;");

    private final String query;

    public String getQuery() {
        return query;
    }

    SqlQueriesDelete(String query) {
        this.query = query;
    }
}




