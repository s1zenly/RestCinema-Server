package ru.hse.softwear.cinemaworld.restServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

import java.util.List;
import java.util.Map;

@Data
public class FilmPageDTO {
    private FilmModel film;
    private Map<CinemaModel, List<SessionModel>> cinemasWithSession;
}
