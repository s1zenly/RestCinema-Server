package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.CinemaMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;


@Mapper(componentModel = "spring",
        uses = {
                CinemaMapperWithoutDependency.class,
                SessionMapperWithoutDependency.class
        })
public interface FilmMapper extends DataMapper<Film, FilmModel> {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Override
    @Mapping(target = "cinemas", source = "cinemas", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "sessions", source = "sessions", qualifiedByName = "toEntityWithoutDependency")
    Film toEntity(FilmModel filmModel);

    @Override
    @Mapping(target = "cinemas", source = "cinemas", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "sessions", source = "sessions", qualifiedByName = "toModelWithoutDependency")
    FilmModel toModel(Film film);
}
