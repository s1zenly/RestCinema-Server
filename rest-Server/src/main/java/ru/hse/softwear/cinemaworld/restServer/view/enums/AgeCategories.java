package ru.hse.softwear.cinemaworld.restServer.view.enums;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum AgeCategories {
    ZERO("0+"),
    SIX("6+"),
    TWELVE("12+"),
    SIXTEEN("16+"),
    EIGHTEEN("18+");

    private String ageCategory;
}
