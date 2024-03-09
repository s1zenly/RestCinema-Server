package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.model.FilmModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-10T01:23:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class FilmMapperWithoutDependencyImpl implements FilmMapperWithoutDependency {

    @Override
    public Film toEntityWithoutDependency(FilmModel filmModel) {
        if ( filmModel == null ) {
            return null;
        }

        Film film = new Film();

        film.setName( filmModel.getName() );
        film.setAgeCategory( filmModel.getAgeCategory() );
        film.setYear( filmModel.getYear() );
        film.setProductionCountry( filmModel.getProductionCountry() );
        film.setProducer( filmModel.getProducer() );
        film.setDuration( filmModel.getDuration() );
        List<String> list = filmModel.getActors();
        if ( list != null ) {
            film.setActors( new ArrayList<String>( list ) );
        }
        film.setTrailerURL( filmModel.getTrailerURL() );
        film.setInfo( filmModel.getInfo() );
        film.setCurrent( filmModel.getCurrent() );
        film.setImageURL( filmModel.getImageURL() );

        return film;
    }

    @Override
    public FilmModel toModelWithoutDependency(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmModel filmModel = new FilmModel();

        filmModel.setName( film.getName() );
        filmModel.setAgeCategory( film.getAgeCategory() );
        filmModel.setYear( film.getYear() );
        filmModel.setProductionCountry( film.getProductionCountry() );
        filmModel.setProducer( film.getProducer() );
        filmModel.setDuration( film.getDuration() );
        List<String> list = film.getActors();
        if ( list != null ) {
            filmModel.setActors( new ArrayList<String>( list ) );
        }
        filmModel.setTrailerURL( film.getTrailerURL() );
        filmModel.setInfo( film.getInfo() );
        filmModel.setCurrent( film.getCurrent() );
        filmModel.setImageURL( film.getImageURL() );

        return filmModel;
    }
}
