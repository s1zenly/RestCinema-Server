package ru.hse.softwear.cinemaworld.userServer.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketPageDTO {
    private SessionModel session;
    private String filmName;
    private String CinemaName;
    private String hallName;
    private Integer countTickets;

}
