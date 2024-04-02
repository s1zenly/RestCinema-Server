package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T10:53:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class CinemaMapperWithoutDependencyImpl implements CinemaMapperWithoutDependency {

    @Override
    public Cinema toEntityWithoutDependency(CinemaModel cinemaModel) {
        if ( cinemaModel == null ) {
            return null;
        }

        Cinema cinema = new Cinema();

        cinema.setId( cinemaModel.getId() );
        cinema.setName( cinemaModel.getName() );
        cinema.setLatitude( cinemaModel.getLatitude() );
        cinema.setLongitude( cinemaModel.getLongitude() );
        cinema.setInfo( cinemaModel.getInfo() );
        cinema.setNumberPhone( cinemaModel.getNumberPhone() );
        cinema.setImage( cinemaModel.getImage() );

        return cinema;
    }

    @Override
    public CinemaModel toModelWithoutDependency(Cinema cinema) {
        if ( cinema == null ) {
            return null;
        }

        CinemaModel cinemaModel = new CinemaModel();

        cinemaModel.setId( cinema.getId() );
        cinemaModel.setName( cinema.getName() );
        cinemaModel.setLatitude( cinema.getLatitude() );
        cinemaModel.setLongitude( cinema.getLongitude() );
        cinemaModel.setInfo( cinema.getInfo() );
        cinemaModel.setNumberPhone( cinema.getNumberPhone() );
        cinemaModel.setImage( cinema.getImage() );

        return cinemaModel;
    }
}
