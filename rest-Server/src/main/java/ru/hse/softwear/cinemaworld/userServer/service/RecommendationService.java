package ru.hse.softwear.cinemaworld.userServer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.model.CinemaWithSessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.CoordinateModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final GeolocationService geolocationService;

    public List<CinemaWithSessionModel> recommendationCinemas(List<CinemaWithSessionModel> cinemas,
                                                              CoordinateModel coordinate) {
        return recommendedByGeolocation(cinemas, coordinate);
    }


    public List<CinemaWithSessionModel> recommendedByGeolocation(List<CinemaWithSessionModel> cinemas,
                                                                 CoordinateModel coordinate) {


        Comparator<CinemaWithSessionModel> comparatorByGeolocation = (cinema1, cinema2) -> {
            double distanceCinema1 = geolocationService.calculateDistance(cinema1.getCinema().getLatitude(),
                    coordinate.getLatitude(), cinema1.getCinema().getLongitude(), coordinate.getLongitude());

            double distanceCinema2 = geolocationService.calculateDistance(cinema2.getCinema().getLatitude(),
                    coordinate.getLatitude(), cinema2.getCinema().getLongitude(), coordinate.getLongitude());

            return Double.compare(distanceCinema1, distanceCinema2);
        };

        return cinemas.stream()
                .sorted(comparatorByGeolocation)
                .toList();
    }





}
