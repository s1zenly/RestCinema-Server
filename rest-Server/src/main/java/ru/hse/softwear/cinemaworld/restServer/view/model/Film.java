package ru.hse.softwear.cinemaworld.restServer.view.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.restServer.view.enums.AgeCategories;

import java.time.Duration;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age_category")
    private AgeCategories ageCategory;

    @Column(name = "year")
    private Integer year;

    @Column(name = "production_country")
    private String productionCountry;

    @Column(name = "producer")
    private String producer;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "actors")
    private List<String> actors;

    @Lob
    @Column(name = "trailer")
    private byte[] trailer;

    @Column(name = "info")
    private String info;

    @Column(name = "current")
    private Boolean current;

    @Lob
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private ImageFilm image;

    @ManyToMany
    private List<Cinema> cinemas;
}
