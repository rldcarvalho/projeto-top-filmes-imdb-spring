package br.com.rldcarvalho.dayscode.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMovies{
    private List<Movie> items;

    public ListOfMovies() {
    }

    public ListOfMovies(List<Movie> items) {
        this.items = items;
    }

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }

    public static Map<String, Movie> creatMapWithId(ListOfMovies movies){
        Map<String, Movie> moviesMap = new HashMap<>();
        int id = 1;
        for (Movie movie : movies.getItems()) {
            moviesMap.put(String.valueOf(id), movie);
            id++;
        }
        return moviesMap;
    }
}
