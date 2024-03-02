package ru.hse.softwear.cinemaworld.admin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.HallRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.PosterRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CinemaController {

    private final PosterRepository posterRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    @PostMapping("/cinema")
    public void addCinema(@RequestBody Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    @DeleteMapping("/cinema")
    public void deleteCinema(@RequestBody Cinema cinema) {
        cinemaRepository.delete(cinema);
    }

    @GetMapping("/cinema/{cinemaName}")
    public ResponseEntity<Cinema> getCinema(@PathVariable String cinemaName) {
        Cinema cinema = cinemaRepository.findByName(cinemaName);
        return cinema != null
                ? ResponseEntity.ok(cinema)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{cinemaName}")
    public void updateCinema(@PathVariable Cinema cinema) {

    }


}