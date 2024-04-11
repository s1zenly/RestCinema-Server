/*
package ru.hse.softwear.cinemaworld.userServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.userServer.service.CinemaService;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CinemaPageController {

    private final CinemaService cinemaService;

    @GetMapping("/cinemas")
    public ResponseEntity<List<CinemaModel>> getAllCinema() {
        return ResponseEntity.ok(cinemaService.getAllCinemas());
    }

    @GetMapping("/cinema/{id}")
    public ResponseEntity<CinemaModel> getCinema(@PathVariable Long id) {
        return ResponseEntity.ok(cinemaService.getCinema(id));
    }

}
*/
