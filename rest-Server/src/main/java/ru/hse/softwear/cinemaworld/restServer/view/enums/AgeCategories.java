package ru.hse.softwear.cinemaworld.restServer.view.enums;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum AgeCategories {
    ZERO("ZERO"),
    SIX("SIX"),
    TWELVE("TWELVE"),
    SIXTEEN("SIXTEEN"),
    EIGHTEEN("EIGHTEEN");

    private String value;
}
