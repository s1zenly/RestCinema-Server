package ru.hse.softwear.cinemaworld.userServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;

import java.util.List;

@Data
public class HomePageDTO {
    private List<FilmModel> films;
    private List<CinemaModel> cinemas;
}
