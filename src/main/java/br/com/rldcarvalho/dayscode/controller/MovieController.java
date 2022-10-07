package br.com.rldcarvalho.dayscode.controller;

import br.com.rldcarvalho.dayscode.HTMLGenerator;
import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import br.com.rldcarvalho.dayscode.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class MovieController {

    @Value("${url.imdb}")
    private String baseUrl;
    @Value("${api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/top250")
    public ListOfMovies getTop250Films() throws IOException {
        ResponseEntity<ListOfMovies> response
                = this.restTemplate.getForEntity(baseUrl + apiKey, ListOfMovies.class);

        PrintWriter ps = new PrintWriter("src/main/resources/content.html", StandardCharsets.UTF_8);

        new HTMLGenerator(ps).generate(response.getBody().getItems());

        ps.close();

        return response.getBody();
    }
}
