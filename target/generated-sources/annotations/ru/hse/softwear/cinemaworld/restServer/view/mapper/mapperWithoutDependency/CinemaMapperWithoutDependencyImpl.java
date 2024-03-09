package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-10T01:23:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class CinemaMapperWithoutDependencyImpl implements CinemaMapperWithoutDependency {

    @Override
    public Cinema toEntityWithoutDependency(CinemaModel cinemaModel) {
        if ( cinemaModel == null ) {
            return null;
        }

        Cinema cinema = new Cinema();

        cinema.setName( cinemaModel.getName() );
        cinema.setCoordinates( cinemaModel.getCoordinates() );
        cinema.setRating( cinemaModel.getRating() );
        cinema.setInfo( cinemaModel.getInfo() );
        cinema.setNumberPhone( cinemaModel.getNumberPhone() );
        cinema.setPreviewURL( cinemaModel.getPreviewURL() );
        List<String> list = cinemaModel.getImages();
        if ( list != null ) {
            cinema.setImages( new ArrayList<String>( list ) );
        }

        return cinema;
    }

    @Override
    public CinemaModel toModelWithoutDependency(Cinema cinema) {
        if ( cinema == null ) {
            return null;
        }

        CinemaModel cinemaModel = new CinemaModel();

        cinemaModel.setName( cinema.getName() );
        cinemaModel.setCoordinates( cinema.getCoordinates() );
        cinemaModel.setRating( cinema.getRating() );
        cinemaModel.setInfo( cinema.getInfo() );
        cinemaModel.setNumberPhone( cinema.getNumberPhone() );
        cinemaModel.setPreviewURL( cinema.getPreviewURL() );
        List<String> list = cinema.getImages();
        if ( list != null ) {
            cinemaModel.setImages( new ArrayList<String>( list ) );
        }

        return cinemaModel;
    }
}
