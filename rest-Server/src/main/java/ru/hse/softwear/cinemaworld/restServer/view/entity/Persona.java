package ru.hse.softwear.cinemaworld.restServer.view.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.restServer.view.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personalities")
public class Persona {

    @Id
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
