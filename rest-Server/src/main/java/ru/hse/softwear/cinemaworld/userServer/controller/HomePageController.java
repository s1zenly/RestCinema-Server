package ru.hse.softwear.cinemaworld.userServer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.userServer.service.InfoService;
import ru.hse.softwear.cinemaworld.userServer.view.dto.HomePageDTO;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomePageController {

    private final InfoService infoService;

    @GetMapping
    public ResponseEntity<HomePageDTO> getAllMainInfo() {
        HomePageDTO homePageDTO = new HomePageDTO();

        List<CinemaModel> cinemas = infoService.getAllCinema();
        List<FilmModel> films = infoService.getAllFilm();

        homePageDTO.setFilms(films);
        homePageDTO.setCinemas(cinemas);

        return ResponseEntity.ok(homePageDTO);
    }

    @GetMapping("/films/current")
    public ResponseEntity<List<FilmModel>> getCurrentFilms() {
        return ResponseEntity.ok(infoService.getCurrentFilm());
    }

    @GetMapping("/films/soon")
    public ResponseEntity<List<FilmModel>> getSoonFilms() {
        return ResponseEntity.ok(infoService.getSoonFilms());
    }

}
