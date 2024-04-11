package ru.hse.softwear.cinemaworld.adminServer.view.dto;

import lombok.Data;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

@Data
public class SessionPageDTO {
    private SessionModel session;
    private String filmName;
    private String hallName;
}
