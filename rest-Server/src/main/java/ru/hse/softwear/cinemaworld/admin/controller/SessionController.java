package ru.hse.softwear.cinemaworld.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.admin.service.crudServices.SessionService;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class SessionController {

    private final SessionService sessionService;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/session")
    public void addSession(@RequestParam Long filmId,
                           @RequestParam Long hallId,
                           @RequestBody SessionModel sessionDTO) {

        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        sessionService.create(cinemaId, filmId, hallId, sessionDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/session/{id}")
    public void updateSession(@PathVariable Long id,
                              @RequestBody SessionModel sessionDTO) {
        sessionService.update(id, sessionDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/session/{id}")
    public void deleteSession(@PathVariable Long id) {
        sessionService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/session/{id}")
    public ResponseEntity<SessionModel> getSession(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.read(id));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/session")
    public ResponseEntity<List<SessionModel>> getAllSession() {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        List<SessionModel> sessions = sessionService.getAll(cinemaId);

        return sessions.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(sessions);
    }
}
