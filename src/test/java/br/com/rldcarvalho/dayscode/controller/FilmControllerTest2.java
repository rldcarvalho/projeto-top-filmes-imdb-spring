package br.com.rldcarvalho.dayscode.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class FilmControllerTest2 {

    @Autowired
    private FilmController filmController;

    @Test
    void getTop250Films() {
        String json = filmController.getTop250Films();

        assertNotNull(json);
    }
}