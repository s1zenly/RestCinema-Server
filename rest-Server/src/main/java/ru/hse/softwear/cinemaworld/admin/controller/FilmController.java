package ru.hse.softwear.cinemaworld.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.admin.service.crudServices.FilmService;
import ru.hse.softwear.cinemaworld.restServer.view.model.FilmModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/{cinemaName}")
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/film")
    public void addFilm(@PathVariable String cinemaName,
                        @RequestBody FilmModel filmDTO) {
        filmService.create(cinemaName, filmDTO);
    }

    @PutMapping("/film/{filmName}")
    public void updateFilm(@PathVariable String filmName,
                           @RequestBody FilmModel filmDTO) {

        filmService.update(filmName, filmDTO);
    }

    @GetMapping("/film/{filmName}")
    public ResponseEntity<FilmModel> getFilm(@PathVariable String filmName) {
        FilmModel filmModel = filmService.read(filmName);

        return filmModel == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(filmModel);
    }

    @DeleteMapping("/film/{filmName}")
    public void deleteFilm(@PathVariable String cinemaName,
                           @PathVariable String filmName) {
        filmService.delete(cinemaName, filmName);
    }

    @GetMapping("/film")
    public ResponseEntity<List<FilmModel>> getAllFilms(@PathVariable String cinemaName) {
        List<FilmModel> films = filmService.getAll(cinemaName);

        return films == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(films);
    }


}