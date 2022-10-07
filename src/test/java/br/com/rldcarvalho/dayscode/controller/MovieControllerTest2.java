package br.com.rldcarvalho.dayscode.controller;

import br.com.rldcarvalho.dayscode.model.ListOfMovies;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class MovieControllerTest2 {

    @Autowired
    private MovieController movieController;

    @Test
    void getTop250Films() {
        ListOfMovies json = movieController.getTop250Films();

        assertNotNull(json);
    }
}