package ru.hse.softwear.cinemaworld.userServer.view.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN"),
    PROGRAMMER("PROGRAMMER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }

}
