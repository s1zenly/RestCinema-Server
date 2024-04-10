package ru.hse.softwear.cinemaworld.authServer.util;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import ru.hse.softwear.cinemaworld.authServer.service.PersonaService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Admin;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.enums.Role;

@RequiredArgsConstructor
public class JwtUtils {

    private final PersonaService personaService;

    public static JwtAuthentication generate(Claims claims, User user, Admin admin) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setEmail(claims.getSubject());
        jwtInfoToken.setRole(getRole(claims));
        jwtInfoToken.setId(getPrincipalInfo(user, admin));

        return jwtInfoToken;
    }

    private static Role getRole(Claims claims) {
        String role = claims.get("role", String.class);
        return Role.valueOf(role);
    }

    private static Long getPrincipalInfo(User user, Admin admin) {
        if(user != null) {
            return user.getId();
        }
        if(admin != null) {
            return admin.getCinemaId();
        }

        return null;
    }
}
