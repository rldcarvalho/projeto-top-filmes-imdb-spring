package br.com.rldcarvalho.dayscode.controller;

import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
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
        ListOfMovies json = movieController.getTop250Movies();

        assertNotNull(json);
    }
}