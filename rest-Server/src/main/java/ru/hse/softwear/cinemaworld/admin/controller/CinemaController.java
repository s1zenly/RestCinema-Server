package ru.hse.softwear.cinemaworld.admin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.admin.service.crudServices.CinemaService;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CinemaController {

    private final CinemaService cinemaService;

    @PostMapping("/cinema")
    public void addCinema(@RequestBody CinemaModel cinemaDTO) {
        cinemaService.create(cinemaDTO);
    }

    @PutMapping("/cinema/{name}")
    public void updateCinema(@PathVariable String name,
                             @RequestBody CinemaModel cinemaDTO) {

        cinemaService.update(name, cinemaDTO);
    }

    @GetMapping("/cinema/{name}")
    public ResponseEntity<CinemaModel> getCinema(@PathVariable String name) {
        CinemaModel cinemaDTO = cinemaService.read(name);
        return cinemaDTO == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cinemaDTO);
    }

    @DeleteMapping("/cinema/{name}")
    public void deleteCinema(@PathVariable String name) {
        cinemaService.delete(name);
    }

}