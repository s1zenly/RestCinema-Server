package ru.hse.softwear.cinemaworld.adminServer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.adminServer.service.CinemaServiceAdmin;
import ru.hse.softwear.cinemaworld.adminServer.view.model.CinemaUpdateModel;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CinemaControllerAdmin {

    private final CinemaServiceAdmin cinemaServiceAdmin;
    private final AuthService authService;

    // Programmer method

    @PostMapping("/cinema")
    public void addCinema(@RequestBody CinemaModel cinemaDTO) {
        cinemaServiceAdmin.create(cinemaDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/cinema")
    public void updateCinema(@RequestBody CinemaUpdateModel changes) {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        cinemaServiceAdmin.update(cinemaId, changes);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/cinema")
    public ResponseEntity<CinemaModel> getCinema() {
        final JwtAuthentication jwtInfo = authService.getAuthInfo();
        Long cinemaId = (Long) jwtInfo.getPrincipal();

        CinemaModel cinemaDTO = cinemaServiceAdmin.read(cinemaId);
        return cinemaDTO == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cinemaDTO);
    }


    // Programmer method

    @DeleteMapping("/cinema/{id}")
    public void deleteCinema(@PathVariable Long id) {
        cinemaServiceAdmin.delete(id);
    }

}