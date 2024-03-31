package ru.hse.softwear.cinemaworld.restServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.HallModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

import java.util.List;

@Data
public class OrderPageDTO {
    private SessionModel sessionModel;
    private List<OccupiedPlace> occupiedPlaces;
}
