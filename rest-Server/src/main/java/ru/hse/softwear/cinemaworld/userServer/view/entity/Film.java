package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.EnumMapping;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.hse.softwear.cinemaworld.userServer.view.enums.AgeCategories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "films")
public class Film {

    @Id
    private Long id;
    private String name;
    private Integer year;
    private String producer;
    private Duration duration;
    private String actors;
    private String trailer;
    private String info;
    private Boolean current;
    private String image;

    @Column("age_category")
    private AgeCategories ageCategory;
    @Column("production_country")
    private String productionCountry;
}