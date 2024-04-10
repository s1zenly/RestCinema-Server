package ru.hse.softwear.cinemaworld.userServer.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OccupiedPlace implements Serializable {
    private Integer row;
    private Integer column;
}
