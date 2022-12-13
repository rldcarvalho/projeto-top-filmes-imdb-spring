package br.com.rldcarvalho.dayscode.controller;

import br.com.rldcarvalho.dayscode.HTMLGenerator;
import br.com.rldcarvalho.dayscode.client.ImdbApiClient;
import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import br.com.rldcarvalho.dayscode.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    private ListOfMovies movies = new ListOfMovies(new ArrayList<>());
    private ListOfMovies favoritos = new ListOfMovies(new ArrayList<>());
    private Map<String, Movie> moviesMap = new HashMap<>();

    @Autowired
    private ImdbApiClient imdbApiClient;


    @GetMapping("/top250")
    @ResponseBody
    public ListOfMovies getTop250Movies(@RequestParam(required = false) String title) throws IOException {

        movies = this.imdbApiClient.getBody();

        if(title != null){

            ListOfMovies filteredMovies = new ListOfMovies(new ArrayList<>());

            filteredMovies.getItems().addAll(movies.getItems()
            .stream()
            .filter(movie -> movie.getTitle().contains(title))
            .collect(Collectors.toList()));

            movies = filteredMovies;
        }

        generateHtml(movies);

        this.moviesMap = creatMapWithId(movies);

        return movies;

    }

    @PostMapping("/favorito/{filmeId}")
    public String addFavoriteMovie(@PathVariable String filmeId) throws IOException {

        if (this.movies.getItems().isEmpty()) {
            getTop250Movies(null);
        }

        if(this.moviesMap.get(filmeId) != null) {
            this.favoritos.getItems().add(this.moviesMap.get(filmeId));
            return "Filme inserido com sucesso";
        }

        return "ID não encontrado na lista de Filmes";
    }

    @GetMapping("/favoritos")
    public ListOfMovies getFavoriteMovies() throws IOException {

        if(!this.favoritos.getItems().isEmpty()){
            PrintWriter ps = new PrintWriter("src/main/resources/favoritos.html", StandardCharsets.UTF_8);

            new HTMLGenerator(ps).generate(this.favoritos.getItems());

            ps.close();

            System.out.println("Arquivo HTML gerado com sucesso!");
        }

        return this.favoritos;

    }

    public void generateHtml(ListOfMovies movies) throws IOException {

        PrintWriter ps = new PrintWriter("src/main/resources/content.html", StandardCharsets.UTF_8);

        new HTMLGenerator(ps).generate(movies.getItems());

        ps.close();

        System.out.println("Arquivo HTML gerado com sucesso!");
    }
    
    public Map<String, Movie> creatMapWithId(ListOfMovies movies){
        Map<String, Movie> moviesMap = new HashMap<>();
        int id = 1;
        for (Movie movie : movies.getItems()) {
            moviesMap.put(String.valueOf(id), movie);
            id++;
        }
        return moviesMap;
    }
}
