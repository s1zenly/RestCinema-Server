package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.hse.softwear.cinemaworld.userServer.view.enums.Role;

@Data
@Table(name = "personalities")
public class Persona {

    @Id
    private String email;
    private String password;
    private Role role = Role.getDefaultValue();
}
