package ru.hse.softwear.cinemaworld.userServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.model.CinemaWithSessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.List;
import java.util.Map;

@Data
public class FilmPageDTO {
    private FilmModel film;
    private List<CinemaWithSessionModel> cinemasWithSession;
}
