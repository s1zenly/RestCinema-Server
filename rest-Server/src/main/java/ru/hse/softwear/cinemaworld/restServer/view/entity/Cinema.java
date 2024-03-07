package ru.hse.softwear.cinemaworld.restServer.view.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinema")
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

    @Column(name = "preview_url")
    private String previewURL;

    @Column(name = "images_url")
    private List<String> images;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_cinema",
            joinColumns = @JoinColumn(name = "film"),
            inverseJoinColumns = @JoinColumn(name = "cinema")
    )
    private List<Film> films;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true,
    mappedBy = "cinema")
    private List<Hall> halls;
}
