package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "cinemas")
public class Cinema {

    @Id
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String info;
    private String image;

    @Column("number_phone")
    private Long numberPhone;
}
