package ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private Long id;
    private String token;
}
