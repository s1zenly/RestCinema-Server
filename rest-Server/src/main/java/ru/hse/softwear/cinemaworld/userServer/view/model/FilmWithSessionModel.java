package ru.hse.softwear.cinemaworld.userServer.view.model;

import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.List;

@Data
public class FilmWithSessionModel {
    private FilmModel film;
    private List<SessionModel> sessions;
}
