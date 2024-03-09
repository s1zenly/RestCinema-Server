package ru.hse.softwear.cinemaworld.restServer.view.model;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private Long id;

    private SessionModel session;
    private UserModel user;
    private List<TicketModel> tickets;
}
