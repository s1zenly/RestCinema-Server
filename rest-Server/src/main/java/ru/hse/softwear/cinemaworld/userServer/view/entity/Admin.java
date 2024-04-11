package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "admins")
public class Admin {

    @Id
    private Long id;
    private String email;
    private String password;

    // reference
    private Long cinemaId;
}
