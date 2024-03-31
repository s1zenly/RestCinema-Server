package ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionModel {
    private Long id;
    private Date date;
    private Integer price;

    private HallModel hall;
    private CinemaModel cinema;
    private FilmModel film;
    private List<TicketModel> tickets;
}
