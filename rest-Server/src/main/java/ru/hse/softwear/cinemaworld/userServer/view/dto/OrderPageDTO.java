package ru.hse.softwear.cinemaworld.userServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.List;

@Data
public class OrderPageDTO {
    private SessionModel sessionModel;
    private List<OccupiedPlace> occupiedPlaces;
}
