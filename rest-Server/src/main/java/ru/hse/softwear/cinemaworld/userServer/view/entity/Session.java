package ru.hse.softwear.cinemaworld.userServer.view.entity;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;


@Data
@Table(name = "sessions")
public class Session {
    @Id
    private Long id;
    private Date date;
    private Integer price;

    // reference
    private Long cinemaId;
    private Long hallId;
    private Long filmId;
}
