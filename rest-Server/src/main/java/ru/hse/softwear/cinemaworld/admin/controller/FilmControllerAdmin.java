package ru.hse.softwear.cinemaworld.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.admin.service.crudServices.CinemaService;
import ru.hse.softwear.cinemaworld.admin.service.crudServices.FilmServiceAdmin;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FilmControllerAdmin {

    private final FilmServiceAdmin filmService;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/film")
    public void addFilm(@RequestBody FilmModel filmDTO) {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        filmService.create(cinemaId, filmDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/film/{filmId}")
    public void updateFilm(@PathVariable Long filmId,
                           @RequestBody FilmModel filmDTO) {
        filmService.update(filmId, filmDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/film/{filmId}")
    public ResponseEntity<FilmModel> getFilm(@PathVariable Long filmId) {
        FilmModel filmModel = filmService.read(filmId);

        return filmModel == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(filmModel);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/film/{filmId}")
    public void deleteFilm(@PathVariable Long filmId) {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        filmService.delete(cinemaId, filmId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/film")
    public ResponseEntity<List<FilmModel>> getAllFilms() {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        List<FilmModel> films = filmService.getAll(cinemaId);

        return films == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(films);
    }


}