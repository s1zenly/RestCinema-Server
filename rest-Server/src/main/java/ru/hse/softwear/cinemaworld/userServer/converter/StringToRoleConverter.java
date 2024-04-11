package ru.hse.softwear.cinemaworld.userServer.converter;

import org.springframework.core.convert.converter.Converter;
import ru.hse.softwear.cinemaworld.userServer.view.enums.Role;

public class StringToRoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        for(Role role : Role.values()) {
            if(role.getValue().equals(source)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Unknown enum value: " + source);
    }
}
