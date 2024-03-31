package ru.hse.softwear.cinemaworld.restServer.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OccupiedPlace {
    private Integer row;
    private Integer column;
}
