package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.CinemaMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-01T18:40:15+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class FilmMapperImpl implements FilmMapper {

    @Autowired
    private CinemaMapperWithoutDependency cinemaMapperWithoutDependency;
    @Autowired
    private SessionMapperWithoutDependency sessionMapperWithoutDependency;

    @Override
    public Film toEntity(FilmModel filmModel) {
        if ( filmModel == null ) {
            return null;
        }

        Film film = new Film();

        film.setCinemas( cinemaModelListToCinemaList( filmModel.getCinemas() ) );
        film.setSessions( sessionModelListToSessionList( filmModel.getSessions() ) );
        film.setId( filmModel.getId() );
        film.setName( filmModel.getName() );
        film.setAgeCategory( filmModel.getAgeCategory() );
        film.setYear( filmModel.getYear() );
        film.setProductionCountry( filmModel.getProductionCountry() );
        film.setProducer( filmModel.getProducer() );
        film.setDuration( filmModel.getDuration() );
        film.setActors( filmModel.getActors() );
        film.setTrailerURL( filmModel.getTrailerURL() );
        film.setInfo( filmModel.getInfo() );
        film.setCurrent( filmModel.getCurrent() );
        film.setImage( filmModel.getImage() );

        return film;
    }

    @Override
    public FilmModel toModel(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmModel filmModel = new FilmModel();

        filmModel.setCinemas( cinemaListToCinemaModelList( film.getCinemas() ) );
        filmModel.setSessions( sessionListToSessionModelList( film.getSessions() ) );
        filmModel.setId( film.getId() );
        filmModel.setName( film.getName() );
        filmModel.setAgeCategory( film.getAgeCategory() );
        filmModel.setYear( film.getYear() );
        filmModel.setProductionCountry( film.getProductionCountry() );
        filmModel.setProducer( film.getProducer() );
        filmModel.setDuration( film.getDuration() );
        filmModel.setActors( film.getActors() );
        filmModel.setTrailerURL( film.getTrailerURL() );
        filmModel.setInfo( film.getInfo() );
        filmModel.setCurrent( film.getCurrent() );
        filmModel.setImage( film.getImage() );

        return filmModel;
    }

    protected List<Cinema> cinemaModelListToCinemaList(List<CinemaModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Cinema> list1 = new ArrayList<Cinema>( list.size() );
        for ( CinemaModel cinemaModel : list ) {
            list1.add( cinemaMapperWithoutDependency.toEntityWithoutDependency( cinemaModel ) );
        }

        return list1;
    }

    protected List<Session> sessionModelListToSessionList(List<SessionModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Session> list1 = new ArrayList<Session>( list.size() );
        for ( SessionModel sessionModel : list ) {
            list1.add( sessionMapperWithoutDependency.toEntityWithoutDependency( sessionModel ) );
        }

        return list1;
    }

    protected List<CinemaModel> cinemaListToCinemaModelList(List<Cinema> list) {
        if ( list == null ) {
            return null;
        }

        List<CinemaModel> list1 = new ArrayList<CinemaModel>( list.size() );
        for ( Cinema cinema : list ) {
            list1.add( cinemaMapperWithoutDependency.toModelWithoutDependency( cinema ) );
        }

        return list1;
    }

    protected List<SessionModel> sessionListToSessionModelList(List<Session> list) {
        if ( list == null ) {
            return null;
        }

        List<SessionModel> list1 = new ArrayList<SessionModel>( list.size() );
        for ( Session session : list ) {
            list1.add( sessionMapperWithoutDependency.toModelWithoutDependency( session ) );
        }

        return list1;
    }
}
