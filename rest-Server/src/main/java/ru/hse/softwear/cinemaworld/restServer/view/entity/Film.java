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
@Table(name = "film")
public class Film {

    @Id
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
    private String trailerURL;

    @Column(name = "info")
    private String info;

    @Column(name = "current")
    private Boolean current;

    @Lob
    @Column(name = "image_url")
    private String imageURL;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "films")
    @JsonBackReference
    private List<Cinema> cinemas;
}