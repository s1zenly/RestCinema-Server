package ru.hse.softwear.cinemaworld.restServer.view.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    //@Column(name = "seat")
    //private Pair<Integer, Integer> seat;

    @Column(name = "price")
    private Integer price;




    @ManyToOne
    @JoinColumn(name = "custom_id")
    private Custom custom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id")
    private Session session;
}
