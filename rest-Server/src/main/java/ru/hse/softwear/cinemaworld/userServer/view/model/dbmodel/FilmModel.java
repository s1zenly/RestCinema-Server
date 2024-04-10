package ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel;


import lombok.*;
import ru.hse.softwear.cinemaworld.userServer.view.enums.AgeCategories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmModel {

    private Long id;
    private String name;
    private AgeCategories ageCategory;
    private Integer year;
    private String productionCountry;
    private String producer;
    private Duration duration;
    private String actors;
    private String trailerURL;
    private String info;
    private Boolean current;
    private String image;

    private List<CinemaModel> cinemas = new ArrayList<>();
    private List<SessionModel> sessions = new ArrayList<>();
}
