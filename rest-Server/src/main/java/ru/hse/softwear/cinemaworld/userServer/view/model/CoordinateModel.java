package ru.hse.softwear.cinemaworld.userServer.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateModel {
    private Double latitude;
    private Double longitude;
}
