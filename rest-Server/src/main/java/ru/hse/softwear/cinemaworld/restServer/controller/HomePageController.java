package ru.hse.softwear.cinemaworld.restServer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.restServer.view.repository.FilmRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HomePageController {


    @GetMapping
    public ResponseEntity<Map<String, List<?>>> getAllFilms() {
        return ResponseEntity.ok(new HashMap<>());
    }


}
