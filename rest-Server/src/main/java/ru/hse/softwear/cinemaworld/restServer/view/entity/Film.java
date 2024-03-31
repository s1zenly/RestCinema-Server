package ru.hse.softwear.cinemaworld.restServer.view.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.restServer.view.enums.AgeCategories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age_category")
    @Enumerated(EnumType.STRING)
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
    private String actors;

    @Lob
    @Column(name = "trailer")
    private String trailerURL;

    @Column(name = "info")
    private String info;

    @Column(name = "current")
    private Boolean current;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "films")
    private List<Cinema> cinemas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "film")
    private List<Session> sessions = new ArrayList<>();
}