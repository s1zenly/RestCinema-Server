package ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel;


import lombok.*;
import ru.hse.softwear.cinemaworld.restServer.view.enums.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String email;
    private String password;

    private List<OrderModel> orders;
}
