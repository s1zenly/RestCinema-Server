package ru.hse.softwear.cinemaworld.restServer.view.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "image")
    private String image;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "films_cinemas",
            joinColumns = @JoinColumn(name = "films_name"),
            inverseJoinColumns = @JoinColumn(name = "cinemas_name")
    )
    private List<Film> films = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
    mappedBy = "cinema")
    private List<Hall> halls = new ArrayList<>();
}
