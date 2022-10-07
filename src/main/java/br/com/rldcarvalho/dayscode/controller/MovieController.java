package br.com.rldcarvalho.dayscode.controller;

import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Value("${url.imdb}")
    private String baseUrl;
    @Value("${api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/top250")
    public ListOfMovies getTop250Films(){
        ResponseEntity<ListOfMovies> response
                = this.restTemplate.getForEntity(baseUrl + apiKey, ListOfMovies.class);
        System.out.println(response.getBody());
        return response.getBody();
    }
}
