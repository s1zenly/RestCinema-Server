package ru.hse.softwear.cinemaworld.restServer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.model.CoordinateModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

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

    public Map<CinemaModel, List<SessionModel>> recommendationCinemas(Map<CinemaModel, List<SessionModel>> cinemas,
                                                                      CoordinateModel coordinate) {
        return recommendedByGeolocation(cinemas, coordinate);
    }


    public Map<CinemaModel, List<SessionModel>> recommendedByGeolocation(Map<CinemaModel, List<SessionModel>> cinemas,
                                                                         CoordinateModel coordinate) {


        Comparator<CinemaModel> comparatorByGeolocation = (cinema1, cinema2) -> {
            double distanceCinema1 = geolocationService.calculateDistance(cinema1.getLatitude(),
                    coordinate.getLatitude(), cinema1.getLongitude(), coordinate.getLongitude());

            double distanceCinema2 = geolocationService.calculateDistance(cinema2.getLatitude(),
                    coordinate.getLatitude(), cinema2.getLongitude(), coordinate.getLongitude());

            return Double.compare(distanceCinema1, distanceCinema2);
        };

        return cinemas.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(comparatorByGeolocation))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }





}
