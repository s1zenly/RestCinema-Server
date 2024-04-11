package ru.hse.softwear.cinemaworld.userServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.HallModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.List;

@Data
public class OrderPageDTO {
    private SessionModel session;
    private FilmModel film;
    private CinemaModel cinema;
    private HallModel hall;
    private List<OccupiedPlace> occupiedPlaces;
}
