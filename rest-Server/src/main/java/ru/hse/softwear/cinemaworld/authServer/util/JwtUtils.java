package ru.hse.softwear.cinemaworld.authServer.util;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.restServer.view.enums.Role;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setId(claims.get("id", Long.class));
        jwtInfoToken.setEmail(claims.getSubject());
        jwtInfoToken.setRole(getRole(claims));

        return jwtInfoToken;
    }

    private static Role getRole(Claims claims) {
        String role = claims.get("role", String.class);
        return Role.valueOf(role);
    }
}
