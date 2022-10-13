package br.com.rldcarvalho.dayscode.controller;

import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MovieController movieController;

    @Test
    void deveriaRetornarUmJsonComOTop250Filmes() {

        ResponseEntity<ListOfMovies> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/top250", ListOfMovies.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    void deveriaRetornarUmJsonChamadoPelaClasse() throws IOException {
        ListOfMovies json = movieController.getTop250Movies(null);

        assertNotNull(json);
    }

    @Test
    void deveriaRetornarOFilmeQueContemBatmanNoTitulo(){
        ResponseEntity<ListOfMovies> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/top250?title=Batman", ListOfMovies.class);

        assertEquals("Batman Begins", response.getBody().getItems().get(0).getTitle());
    }

    @Test
    void deveriaInserirOFilmeNaListaDeFavoritos(){
        HttpEntity<String> request = new HttpEntity<String>("1");

        ResponseEntity<String> response = this.testRestTemplate.postForEntity("http://localhost:" + port + "/favorito/1", request, String.class);

        assertEquals("Filme inserido com sucesso", response.getBody());
    }

    @Test
    void deveriaGerarUmHtmlComOsFilmesFavoritos(){

        HttpEntity<String> request = new HttpEntity<String>("1");

        ResponseEntity<String> result = this.testRestTemplate.postForEntity("http://localhost:" + port + "/favorito/1", request, String.class);

        ResponseEntity<ListOfMovies> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/favoritos", ListOfMovies.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}