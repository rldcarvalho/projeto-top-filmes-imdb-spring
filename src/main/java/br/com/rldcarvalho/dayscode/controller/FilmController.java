package br.com.rldcarvalho.dayscode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FilmController {

    @Value("${url.imdb}")
    private String baseUrl;
    @Value("${api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/top250")
    public String getTop250Films(){
        ResponseEntity<String> response
                = this.restTemplate.getForEntity(baseUrl + apiKey, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }
}
