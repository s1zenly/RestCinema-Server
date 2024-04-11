package ru.hse.softwear.cinemaworld.adminServer.view.model;

import lombok.Data;

import java.util.Date;

@Data
public class SessionUpdateModel {
    private Date date;
    private Integer price;
}
