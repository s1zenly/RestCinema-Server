package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.FilmModel;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = FilmMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CinemaMapper {

    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);

    Cinema toCinema(CinemaModel cinemaModel, @Context FilmMapper filmMapper);

    CinemaModel toCinemaModel(Cinema cinema, @Context FilmMapper filmMapper);

}
