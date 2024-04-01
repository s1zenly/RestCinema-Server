package ru.hse.softwear.cinemaworld.authServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final AuthService authService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser() {
        final JwtAuthentication authIngo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello" + authIngo.getPrincipal());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin() {
        final JwtAuthentication authIngo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello" + authIngo.getPrincipal());
    }
}
