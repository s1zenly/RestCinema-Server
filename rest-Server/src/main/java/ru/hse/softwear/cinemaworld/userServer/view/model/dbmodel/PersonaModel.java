package ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.userServer.view.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaModel {
    private String email;
    private String password;
    private Role role;
}
