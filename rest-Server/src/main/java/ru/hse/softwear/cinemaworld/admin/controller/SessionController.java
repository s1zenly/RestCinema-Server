package ru.hse.softwear.cinemaworld.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.admin.service.crudServices.SessionService;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/session")
    public void addSession(@RequestBody SessionModel sessionDTO) {
        sessionService.create(sessionDTO);
    }

    @PutMapping("/session/{id}")
    public void updateSession(@PathVariable Long id,
                              @RequestBody SessionModel sessionDTO) {

    }

    @DeleteMapping("/session/{id}")
    public void deleteSession(@PathVariable Long id) {

    }

    @GetMapping("/session/{id}")
    public ResponseEntity<SessionModel> getSession(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/session")
    public ResponseEntity<List<SessionModel>> getAllSession() {
        return null;
    }
}
