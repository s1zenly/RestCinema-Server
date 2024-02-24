package ru.hse.softwear.cinemaworld.restServer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.restServer.view.model.Film;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CurrentPosterRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.SoonPosterRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.interfaces.PosterRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final CurrentPosterRepository currentPosterRepository;
    private final SoonPosterRepository soonPosterRepository;

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> poster = new ArrayList<>();
        poster.addAll(currentPosterRepository.findAll());
        poster.addAll(soonPosterRepository.findAll());

        return ResponseEntity.ok(poster);
    }


}
