package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
        uses = CinemaMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmModel toFilmModel(Film film, @Context CinemaMapper cinemaMapper);
    Film toFilm(FilmModel filmModel, @Context CinemaMapper cinemaMapper);

}
