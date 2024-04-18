package ru.hse.softwear.cinemaworld.userServer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<HomePageDTO> getAllMainInfo(@RequestParam(required = false) Long id) {
        HomePageDTO homePageDTO = new HomePageDTO();

        List<CinemaModel> cinemas = infoService.getAllCinema();
        List<FilmModel> currentFilms = infoService.getCurrentFilm();
        List<FilmModel> soonFilms = infoService.getSoonFilms();

        if(id != null) {
            currentFilms = infoService.getFilmsByCinema(currentFilms, id);
            soonFilms = infoService.getFilmsByCinema(soonFilms, id);
        }

        homePageDTO.setCurrentFilms(currentFilms);
        homePageDTO.setSoonFilms(soonFilms);
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
