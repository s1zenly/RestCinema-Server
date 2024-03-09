package ru.hse.softwear.cinemaworld.admin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.CinemaMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.FilmRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CinemaController {

    private final CinemaRepository cinemaRepository;
    private final FilmRepository filmRepository;

    @PostMapping("/cinema")
    public void addCinema(@RequestBody Cinema cinema) {
        /*List<Film> films = cinema.getFilms();
        films.forEach(film -> {
            film.getCinemas().add(cinema);
            filmRepository.save(film);
        });*/

        cinemaRepository.save(cinema);
    }

    @DeleteMapping("/cinema")
    public void deleteCinema(@RequestBody Cinema cinema) {
        cinemaRepository.delete(cinema);
    }

    @GetMapping("/cinema/{cinemaName}")
    public ResponseEntity<CinemaModel> getCinema(@PathVariable String cinemaName) {
        Cinema cinema = cinemaRepository.findByName(cinemaName);
        CinemaModel cinemaModel = CinemaMapper.INSTANCE.toModel(cinema);

        return cinemaModel == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cinemaModel);
    }

    @PutMapping("/{cinemaName}")
    public void updateCinema(@PathVariable Cinema cinema) {

    }


}