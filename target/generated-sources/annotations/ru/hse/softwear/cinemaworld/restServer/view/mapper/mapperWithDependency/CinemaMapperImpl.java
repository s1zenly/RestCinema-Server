package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.FilmMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.HallMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.HallModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-01T01:31:58+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class CinemaMapperImpl implements CinemaMapper {

    @Autowired
    private FilmMapperWithoutDependency filmMapperWithoutDependency;
    @Autowired
    private HallMapperWithoutDependency hallMapperWithoutDependency;

    @Override
    public Cinema toEntity(CinemaModel cinemaModel) {
        if ( cinemaModel == null ) {
            return null;
        }

        Cinema cinema = new Cinema();

        cinema.setFilms( filmModelListToFilmList( cinemaModel.getFilms() ) );
        cinema.setHalls( hallModelListToHallList( cinemaModel.getHalls() ) );
        cinema.setId( cinemaModel.getId() );
        cinema.setName( cinemaModel.getName() );
        cinema.setLatitude( cinemaModel.getLatitude() );
        cinema.setLongitude( cinemaModel.getLongitude() );
        cinema.setRating( cinemaModel.getRating() );
        cinema.setInfo( cinemaModel.getInfo() );
        cinema.setNumberPhone( cinemaModel.getNumberPhone() );
        cinema.setImage( cinemaModel.getImage() );

        return cinema;
    }

    @Override
    public CinemaModel toModel(Cinema cinema) {
        if ( cinema == null ) {
            return null;
        }

        CinemaModel cinemaModel = new CinemaModel();

        cinemaModel.setFilms( filmListToFilmModelList( cinema.getFilms() ) );
        cinemaModel.setHalls( hallListToHallModelList( cinema.getHalls() ) );
        cinemaModel.setId( cinema.getId() );
        cinemaModel.setName( cinema.getName() );
        cinemaModel.setLatitude( cinema.getLatitude() );
        cinemaModel.setLongitude( cinema.getLongitude() );
        cinemaModel.setRating( cinema.getRating() );
        cinemaModel.setInfo( cinema.getInfo() );
        cinemaModel.setNumberPhone( cinema.getNumberPhone() );
        cinemaModel.setImage( cinema.getImage() );

        return cinemaModel;
    }

    protected List<Film> filmModelListToFilmList(List<FilmModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Film> list1 = new ArrayList<Film>( list.size() );
        for ( FilmModel filmModel : list ) {
            list1.add( filmMapperWithoutDependency.toEntityWithoutDependency( filmModel ) );
        }

        return list1;
    }

    protected List<Hall> hallModelListToHallList(List<HallModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Hall> list1 = new ArrayList<Hall>( list.size() );
        for ( HallModel hallModel : list ) {
            list1.add( hallMapperWithoutDependency.toEntityWithoutDependency( hallModel ) );
        }

        return list1;
    }

    protected List<FilmModel> filmListToFilmModelList(List<Film> list) {
        if ( list == null ) {
            return null;
        }

        List<FilmModel> list1 = new ArrayList<FilmModel>( list.size() );
        for ( Film film : list ) {
            list1.add( filmMapperWithoutDependency.toModelWithoutDependency( film ) );
        }

        return list1;
    }

    protected List<HallModel> hallListToHallModelList(List<Hall> list) {
        if ( list == null ) {
            return null;
        }

        List<HallModel> list1 = new ArrayList<HallModel>( list.size() );
        for ( Hall hall : list ) {
            list1.add( hallMapperWithoutDependency.toModelWithoutDependency( hall ) );
        }

        return list1;
    }
}
