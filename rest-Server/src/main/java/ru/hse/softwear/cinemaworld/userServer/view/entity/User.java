package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private String email;
    private String password;
    private String name;

    @Column("number_phone")
    private Long numberPhone;
}
