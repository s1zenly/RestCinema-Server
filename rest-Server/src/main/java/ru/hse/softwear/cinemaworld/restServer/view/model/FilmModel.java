package ru.hse.softwear.cinemaworld.restServer.view.model;


import lombok.*;
import ru.hse.softwear.cinemaworld.restServer.view.enums.AgeCategories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmModel {
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
