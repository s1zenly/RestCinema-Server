package ru.hse.softwear.cinemaworld.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.PosterRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/cinema")
public class FilmController {

    private final PosterRepository posterRepository;
    private final CinemaRepository cinemaRepository;

    @PostMapping("/{cinemaName}/film")
    public void addFilm(
            @PathVariable("cinemaName") String cinemaName,
            @RequestBody List<Film> films) {

        Cinema cinema_ = cinemaRepository.findByName(cinemaName);
        if(cinema_ != null) {
            cinema_.getFilms().addAll(films);
        }
    }

    @DeleteMapping("/{cinemaName}/dele")
    public void deleteFilm(@RequestBody Film film) {

    }


}