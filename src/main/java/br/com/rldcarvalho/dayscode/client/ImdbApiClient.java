package br.com.rldcarvalho.dayscode.client;

import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ImdbApiClient {

    @Value("${url.imdb}")
    private String baseUrl;
    @Value("${api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;

    public ListOfMovies getBody(){

        ResponseEntity<ListOfMovies> response
                = this.restTemplate.getForEntity(baseUrl + apiKey, ListOfMovies.class);

        return response.getBody();
    }
}
