package br.com.rldcarvalho.dayscode.controller;

import br.com.rldcarvalho.dayscode.HTMLGenerator;
import br.com.rldcarvalho.dayscode.client.ImdbApiClient;
import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import br.com.rldcarvalho.dayscode.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    @Autowired
    private ImdbApiClient imdbApiClient;


    @GetMapping("/top250")
    @ResponseBody
    public ListOfMovies getTop250Films(@RequestParam(required = false) String title) throws IOException {

        ListOfMovies movies = this.imdbApiClient.getBody();

        if(title != null){

            ListOfMovies filteredMovies = new ListOfMovies(new ArrayList<>());

            filteredMovies.getItems().addAll(movies.getItems()
            .stream()
            .filter(movie -> movie.getTitle().contains(title))
            .collect(Collectors.toList()));

            movies = filteredMovies;
        }

        generateHtml(movies);

        return movies;

    }


    public void generateHtml(ListOfMovies movies) throws IOException {

        PrintWriter ps = new PrintWriter("src/main/resources/content.html", StandardCharsets.UTF_8);

        new HTMLGenerator(ps).generate(movies.getItems());

        ps.close();

        System.out.println("Arquivo HTML gerado com sucesso!");
    }
}
