package ru.hse.softwear.cinemaworld.restServer.view.model;

import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaModel {
    private String name;
    private Point coordinates;
    private Double rating;
    private String info;
    private Long numberPhone;
    private String image;


    private List<FilmModel> films = new ArrayList<>();
    private List<HallModel> halls = new ArrayList<>();
}
