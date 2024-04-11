package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "cinemas_films")
public class CinemaFilm {
    @Column("cinema_id")
    private String cinemaId;
    @Column("film_id")
    private String filmId;
}
