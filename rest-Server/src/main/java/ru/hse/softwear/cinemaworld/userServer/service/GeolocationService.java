package ru.hse.softwear.cinemaworld.userServer.service;

import org.springframework.stereotype.Service;

@Service
public class GeolocationService {

    private static final double EARTH_RADIUS = 6371.0;


    public double calculateDistance(double latitude1,
                                           double latitude2,
                                           double longitude1,
                                           double longitude2) {

        double latitudeDist = Math.toRadians(latitude1 - latitude2);
        double longitudeDist = Math.toRadians(longitude1 - longitude2);

        double alpha = Math.pow(Math.sin(latitudeDist / 2), 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.pow(Math.sin(longitudeDist / 2), 2);

        double sigma = 2 * Math.atan2(Math.sqrt(alpha), Math.sqrt(1 - alpha));

        return EARTH_RADIUS * sigma;
    }
}
