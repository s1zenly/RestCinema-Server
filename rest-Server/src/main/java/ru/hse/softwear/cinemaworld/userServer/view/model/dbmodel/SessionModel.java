package ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionModel {
    private Long id;
    private Date date;
    private Integer price;
}
