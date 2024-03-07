package ru.hse.softwear.cinemaworld.admin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.CinemaMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.HallRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.FilmRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CinemaController {

    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    @PostMapping("/cinema")
    public void addCinema(@RequestBody Cinema cinema) {
        List<Hall> halls = cinema.getHalls();
        halls.forEach(hall -> hall.setCinema(cinema));
        cinemaRepository.save(cinema);
    }

    @DeleteMapping("/cinema")
    public void deleteCinema(@RequestBody Cinema cinema) {
        cinemaRepository.delete(cinema);
    }

    @GetMapping("/cinema/{cinemaName}")
    public ResponseEntity<CinemaModel> getCinema(@PathVariable String cinemaName) {
        Cinema cinema = cinemaRepository.findByName(cinemaName);
        CinemaModel cinemaModel = CinemaMapper.INSTANCE.toCinemaModel(cinema);

        return cinemaModel == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cinemaModel);
    }

    @PutMapping("/{cinemaName}")
    public void updateCinema(@PathVariable Cinema cinema) {

    }


}