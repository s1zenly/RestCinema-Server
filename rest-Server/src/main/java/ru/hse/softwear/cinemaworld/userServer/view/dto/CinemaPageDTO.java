package ru.hse.softwear.cinemaworld.userServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.model.FilmWithSessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;

import java.util.List;

@Data
public class CinemaPageDTO {
    private CinemaModel cinema;
    private List<FilmWithSessionModel> filmWithSessions;
}
