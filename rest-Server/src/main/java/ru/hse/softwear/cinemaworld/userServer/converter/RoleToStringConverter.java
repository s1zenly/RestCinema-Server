package ru.hse.softwear.cinemaworld.userServer.converter;

import org.springframework.core.convert.converter.Converter;
import ru.hse.softwear.cinemaworld.userServer.view.enums.Role;

public class RoleToStringConverter implements Converter<Role, String> {
    @Override
    public String convert(Role source) {
        return source.getValue();
    }
}
