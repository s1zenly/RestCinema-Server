package ru.hse.softwear.cinemaworld.restServer.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketPageDTO {
    private SessionModel session;
    private Integer countTickets;
}
