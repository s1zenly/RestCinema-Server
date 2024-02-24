package ru.hse.softwear.cinemaworld.adminController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.restServer.view.model.Film;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CurrentPosterRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.SoonPosterRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/film")
public class FilmController {

    private final CurrentPosterRepository currentPosterRepository;
    private final SoonPosterRepository soonPosterRepository;
    private final CinemaRepository cinemaRepository;

    @PostMapping("/add")
    public void addFilm(@RequestBody Film film) {

    }

    @PostMapping("/update")
    public void updateFilm(@RequestBody Film film) {

    }

    @PostMapping("/delete")
    public void deleteFilm(@RequestBody Film film) {

    }


}