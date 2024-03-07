package ru.hse.softwear.cinemaworld.restServer.view.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_name")
    private Cinema cinema;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_name")
    private Film film;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
    mappedBy = "session")
    private List<Ticket> tickets;
}
