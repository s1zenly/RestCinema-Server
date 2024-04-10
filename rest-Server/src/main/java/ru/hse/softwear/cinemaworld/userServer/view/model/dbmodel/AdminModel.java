package ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminModel {
    private Long id;
    private String email;
    private String password;
    private Long cinemaId;
}
