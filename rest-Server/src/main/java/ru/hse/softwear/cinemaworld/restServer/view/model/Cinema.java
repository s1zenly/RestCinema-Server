package ru.hse.softwear.cinemaworld.restServer.view.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coordinates")
    private Point coordinates;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "info")
    private String info;

    @Column(name = "number_phone")
    private Long numberPhone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "preview_id")
    private ImageCinema preview;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private List<ImageCinema> images;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER,
    mappedBy = "cinemas")
    private List<Film> films;

}
