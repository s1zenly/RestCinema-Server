package ru.hse.softwear.cinemaworld.restServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.restServer.service.FilmService;
import ru.hse.softwear.cinemaworld.restServer.service.RecommendationService;
import ru.hse.softwear.cinemaworld.restServer.view.dto.FilmPageDTO;
import ru.hse.softwear.cinemaworld.restServer.view.model.CoordinateModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/film/{id}")
public class FilmPageController {

    private final FilmService filmService;
    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<FilmPageDTO> getFilmPage(@PathVariable Long id,
                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                               @RequestBody CoordinateModel coordinate) {

        FilmPageDTO filmPageDTO = new FilmPageDTO();

        AbstractMap.SimpleEntry<FilmModel, Map<CinemaModel, List<SessionModel>>> infoAboutFilm =
                filmService.getFilm(id, date);

        Map<CinemaModel, List<SessionModel>> cinemaWithSession = infoAboutFilm.getValue();

        if(!cinemaWithSession.isEmpty()) {
            Map<CinemaModel, List<SessionModel>> recommendedCinemas =
                    recommendationService.recommendationCinemas(cinemaWithSession, coordinate);
            List<Map.Entry<CinemaModel, List<SessionModel>>> cinemasWithSessionList =
                    new ArrayList<>(recommendedCinemas.entrySet());

            filmPageDTO.setCinemasWithSession(cinemasWithSessionList);
        }

        filmPageDTO.setFilm(infoAboutFilm.getKey());

        return ResponseEntity.ok(filmPageDTO);
    }

    @GetMapping("/trailer")
    public ResponseEntity<String> getTrailer(@PathVariable Long id) {
        String trailer = filmService.getTrailer(id);

        return trailer == null
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(trailer);
    }
}

