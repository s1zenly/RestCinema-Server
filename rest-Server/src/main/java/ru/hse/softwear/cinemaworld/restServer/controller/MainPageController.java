package ru.hse.softwear.cinemaworld.restServer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.repository.FilmRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final FilmRepository filmRepository;

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok(filmRepository.findAll());
    }


}
