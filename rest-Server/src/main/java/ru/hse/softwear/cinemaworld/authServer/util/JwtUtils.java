package ru.hse.softwear.cinemaworld.authServer.util;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hse.softwear.cinemaworld.authServer.service.PersonaService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Admin;
import ru.hse.softwear.cinemaworld.restServer.view.entity.User;
import ru.hse.softwear.cinemaworld.restServer.view.enums.Role;
import ru.hse.softwear.cinemaworld.restServer.view.repository.AdminRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.UserRepository;

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
