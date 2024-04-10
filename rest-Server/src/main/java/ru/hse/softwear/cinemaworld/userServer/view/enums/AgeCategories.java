package ru.hse.softwear.cinemaworld.userServer.view.enums;

import lombok.*;

@RequiredArgsConstructor
@Getter
public enum AgeCategories {
    ZERO("ZERO"),
    SIX("SIX"),
    TWELVE("TWELVE"),
    SIXTEEN("SIXTEEN"),
    EIGHTEEN("EIGHTEEN");

    private final String value;
}
