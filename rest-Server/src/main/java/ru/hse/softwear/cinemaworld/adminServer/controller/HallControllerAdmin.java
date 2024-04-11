package ru.hse.softwear.cinemaworld.adminServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.adminServer.service.crudServices.HallServiceAdmin;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.HallModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class HallControllerAdmin {

    private final HallServiceAdmin hallServiceAdmin;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/hall")
    public void addHall(@RequestBody HallModel hallDTO) {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        hallServiceAdmin.create(cinemaId, hallDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/hall/{id}")
    public void updateHall(@PathVariable Long id,
                           @RequestBody HallModel hallDTO) {
        hallServiceAdmin.update(id, hallDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/hall/{id}")
    public ResponseEntity<HallModel> getHall(@PathVariable Long id) {
        HallModel hallDto = hallServiceAdmin.read(id);

        return hallDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(hallDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/hall/{id}")
    public void deleteHall(@PathVariable Long id) {
        hallServiceAdmin.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/hall")
    public ResponseEntity<List<HallModel>> getAllHalls() {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        List<HallModel> halls = hallServiceAdmin.getAll(cinemaId);

        return halls == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(halls);
    }
}
