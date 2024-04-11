package ru.hse.softwear.cinemaworld.userServer.view.model;

import lombok.Data;

@Data
public class UserUpdateModel {
    private String name;
    private String password;
    private Long numberPhone;
}
