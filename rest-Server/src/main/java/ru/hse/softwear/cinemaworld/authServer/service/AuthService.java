package ru.hse.softwear.cinemaworld.authServer.service;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.authServer.view.JwtRequest;
import ru.hse.softwear.cinemaworld.authServer.view.JwtResponse;
import ru.hse.softwear.cinemaworld.userServer.service.RedisService;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Persona;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonaService personaService;
    private final RedisService redisService;
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NotNull JwtRequest authRequest) throws AuthException {
        final Persona persona = personaService.getByEmail(authRequest.getEmail())
                .orElseThrow(() -> new AuthException("Persona not found"));

        if(persona.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(persona);
            final String refreshToken = jwtProvider.generateRefreshToken(persona);
            redisService.setInRefreshToken(persona.getEmail(), refreshToken);

            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Invalid password");
        }
    }

    public JwtResponse getAccessToken(@NotNull String refreshToken) throws AuthException {
        if(jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = redisService.getInRefreshToken(email);

            if(saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Persona persona = personaService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Persona not found"));

                final String accessToken = jwtProvider.generateAccessToken(persona);
                return new JwtResponse(accessToken, null);
            }
        }

        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NotNull String refreshToken) throws AuthException {
        if(jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final String saveRefreshToken = redisService.getInRefreshToken(email);

            if(saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Persona persona = personaService.getByEmail(email)
                        .orElseThrow(() -> new AuthException("Persona not found"));

                final String accessToken = jwtProvider.generateAccessToken(persona);
                final String newRefreshToken = jwtProvider.generateRefreshToken(persona);
                redisService.setInRefreshToken(persona.getEmail(), newRefreshToken);

                return new JwtResponse(accessToken, newRefreshToken);
            }
        }

        throw new AuthException("Invalid jwt token");
    }

    public boolean register(String email, String password) {
        Persona persona = personaService.getByEmail(email).orElse(null);

        if(persona == null) {
            personaService.saveUser(email, password);
        }

        return persona == null;
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
