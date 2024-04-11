package ru.hse.softwear.cinemaworld.adminServer.view.model;

import lombok.Data;

@Data
public class HallUpdateModel {
    private String name;
    private Integer rows;
    private Integer columns;
}
