package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "halls")
public class Hall {

    @Id
    private Long id;
    private String name;
    private Integer rows;
    private Integer columns;

    // reference
    private Long cinemaId;
}
