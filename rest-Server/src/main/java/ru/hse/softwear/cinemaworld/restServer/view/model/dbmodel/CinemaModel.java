package ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel;

import jakarta.persistence.Column;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaModel {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String info;
    private Long numberPhone;
    private String image;


    private List<FilmModel> films = new ArrayList<>();
    private List<HallModel> halls = new ArrayList<>();
}
