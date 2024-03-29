package ru.hse.softwear.cinemaworld.restServer.view.model;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HallModel {
    private Long id;
    private String name;
    private Integer rows;
    private Integer columns;


    private CinemaModel cinema;
    private List<SessionModel> sessions;
}
