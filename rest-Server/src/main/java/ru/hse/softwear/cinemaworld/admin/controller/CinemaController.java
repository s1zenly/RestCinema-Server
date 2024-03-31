package ru.hse.softwear.cinemaworld.admin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.admin.service.crudServices.CinemaService;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CinemaController {

    private final CinemaService cinemaService;
    private final AuthService authService;

    // Programmer method
    @PreAuthorize("hasAuthority('PROGRAMMER')")
    @PostMapping("/cinema")
    public void addCinema(@RequestBody CinemaModel cinemaDTO) {
        cinemaService.create(cinemaDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/cinema")
    public void updateCinema(@RequestBody CinemaModel cinemaDTO) {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        cinemaService.update(cinemaId, cinemaDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/cinema")
    public ResponseEntity<CinemaModel> getCinema() {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        CinemaModel cinemaDTO = cinemaService.read(cinemaId);
        return cinemaDTO == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cinemaDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/cinema")
    public void deleteCinema() {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        cinemaService.delete(cinemaId);
    }

}