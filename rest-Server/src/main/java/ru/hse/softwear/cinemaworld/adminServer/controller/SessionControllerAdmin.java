package ru.hse.softwear.cinemaworld.adminServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.adminServer.service.SessionServiceAdmin;
import ru.hse.softwear.cinemaworld.adminServer.view.dto.SessionPageDTO;
import ru.hse.softwear.cinemaworld.adminServer.view.model.SessionUpdateModel;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class SessionControllerAdmin {

    private final SessionServiceAdmin sessionServiceAdmin;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/session")
    public void addSession(@RequestParam Long filmId,
                           @RequestParam Long hallId,
                           @RequestBody SessionModel sessionDTO) {

        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        sessionServiceAdmin.create(cinemaId, filmId, hallId, sessionDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/session/{id}")
    public void updateSession(@PathVariable Long id,
                              @RequestBody SessionUpdateModel changes) {
        sessionServiceAdmin.update(id, changes);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/session/{id}")
    public void deleteSession(@PathVariable Long id) {
        sessionServiceAdmin.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/session/{id}")
    public ResponseEntity<SessionPageDTO> getSession(@PathVariable Long id) {
        SessionModel sessionModel = sessionServiceAdmin.read(id);
        AbstractMap.SimpleEntry<String, String> sessionInfo = sessionServiceAdmin.getSessionInfo(id);

        SessionPageDTO sessionPageDTO = new SessionPageDTO();
        sessionPageDTO.setSession(sessionModel);
        sessionPageDTO.setFilmName(sessionInfo.getKey());
        sessionPageDTO.setHallName(sessionInfo.getValue());

        return ResponseEntity.ok(sessionPageDTO);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/session")
    public ResponseEntity<List<SessionPageDTO>> getAllSession() {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        List<SessionPageDTO> sessionPageDTOs = new ArrayList<>();
        List<SessionModel> sessions = sessionServiceAdmin.getAll(cinemaId);

        for(SessionModel session : sessions) {
            AbstractMap.SimpleEntry<String, String> sessionInfo = sessionServiceAdmin.getSessionInfo(session.getId());

            SessionPageDTO sessionPageDTO = new SessionPageDTO();
            sessionPageDTO.setSession(session);
            sessionPageDTO.setFilmName(sessionInfo.getKey());
            sessionPageDTO.setHallName(sessionInfo.getValue());
        }
        return sessions.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(sessionPageDTOs);
    }
}
