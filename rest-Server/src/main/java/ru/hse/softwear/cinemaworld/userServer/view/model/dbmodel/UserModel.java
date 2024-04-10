package ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Long numberPhone;

    private List<OrderModel> orders;
}
