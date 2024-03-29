package ru.hse.softwear.cinemaworld.restServer.view.model;


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
    private Role role;

    private List<OrderModel> orders;
}
