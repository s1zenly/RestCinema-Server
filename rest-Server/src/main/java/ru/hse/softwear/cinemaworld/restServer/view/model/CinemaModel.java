package ru.hse.softwear.cinemaworld.restServer.view.model;

import lombok.*;
import org.locationtech.jts.geom.Point;

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
    private String previewURL;
    private List<String> images;


    private List<FilmModel> films;
    private List<HallModel> halls;
}
