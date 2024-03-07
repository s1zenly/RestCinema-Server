package ru.hse.softwear.cinemaworld.restServer.view.model;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    private Long id;
    private String email;
    private String password;

    /*private List<CustomModel> customs;*/
}
