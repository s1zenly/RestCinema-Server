package ru.hse.softwear.cinemaworld.userServer.view.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "tickets")
public class Ticket {

    @Id
    private Long id;
    private Integer section;
    private Integer subsection;

    // reference
    private Long orderId;
    private Long sessionId;
}
