package ru.hse.softwear.cinemaworld.userServer.view.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.userServer.view.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personalities")
public class Persona {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;
}
