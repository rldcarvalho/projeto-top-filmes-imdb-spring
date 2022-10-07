package br.com.rldcarvalho.dayscode.model;

import java.util.List;

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
}
