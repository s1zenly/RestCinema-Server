package ru.hse.softwear.cinemaworld.restServer.view.model;



import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {
    private Long id;
    private String name;
    private Date date;
    //private Pair<Integer, Integer> seat;
    private Integer price;


    /*private CustomModel custom;
    private SessionModel session;*/
}
