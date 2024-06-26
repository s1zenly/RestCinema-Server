package ru.hse.softwear.cinemaworld.authServer.controller;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.authServer.view.JwtRequest;
import ru.hse.softwear.cinemaworld.authServer.view.JwtResponse;
import ru.hse.softwear.cinemaworld.authServer.view.RefreshJwtRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody JwtRequest request) throws AuthException {
        boolean success = authService.register(request.getEmail(), request.getPassword());
        JwtResponse token = null;
        if(success) {
            token = authService.login(request);
        }

        return success
                ? ResponseEntity.ok(token)
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }
}
