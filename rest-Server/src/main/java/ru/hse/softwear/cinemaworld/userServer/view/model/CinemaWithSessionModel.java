package ru.hse.softwear.cinemaworld.userServer.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.ArrayList;
import java.util.List;

@Data
public class CinemaWithSessionModel {
    private CinemaModel cinema;
    private List<SessionModel> sessions;
}
