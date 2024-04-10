package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T12:51:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class FilmMapperWithoutDependencyImpl implements FilmMapperWithoutDependency {

    @Override
    public Film toEntityWithoutDependency(FilmModel filmModel) {
        if ( filmModel == null ) {
            return null;
        }

        Film film = new Film();

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
    public FilmModel toModelWithoutDependency(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmModel filmModel = new FilmModel();

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
}
