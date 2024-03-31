package ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel;



import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {
    private Long id;
    private Integer section;
    private Integer subsection;

    private OrderModel order;
    private SessionModel session;
}
