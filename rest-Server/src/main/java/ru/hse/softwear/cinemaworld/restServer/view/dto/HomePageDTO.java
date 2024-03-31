package ru.hse.softwear.cinemaworld.restServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;

import java.util.List;

@Data
public class HomePageDTO {
    private List<FilmModel> films;
    private List<CinemaModel> cinemas;
}
