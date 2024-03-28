package ru.hse.softwear.cinemaworld.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.admin.Service.crudServices.HallService;
import ru.hse.softwear.cinemaworld.restServer.view.model.HallModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/{cinemaName}")
public class HallController {

    private final HallService hallService;

    @PostMapping("/hall")
    public void addHall(@PathVariable String cinemaName,
                        @RequestBody HallModel hallDTO) {
        hallService.create(cinemaName, hallDTO);
    }

    @PutMapping("/hall/{id}")
    public void updateHall(@PathVariable Long id,
                           @RequestBody HallModel hallDTO) {
        hallService.update(id, hallDTO);
    }

    @GetMapping("/hall/{id}")
    public ResponseEntity<HallModel> getHall(@PathVariable Long id) {
        HallModel hallDto = hallService.read(id);

        return hallDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(hallDto);
    }

    @DeleteMapping("/hall/{id}")
    public void deleteHall(@PathVariable String cinemaName,
                           @PathVariable Long id) {
        hallService.delete(cinemaName, id);
    }

    @GetMapping("/hall")
    public ResponseEntity<List<HallModel>> getAllHalls(@PathVariable String cinemaName) {
        List<HallModel> halls = hallService.getAll(cinemaName);

        return halls == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(halls);
    }
}
