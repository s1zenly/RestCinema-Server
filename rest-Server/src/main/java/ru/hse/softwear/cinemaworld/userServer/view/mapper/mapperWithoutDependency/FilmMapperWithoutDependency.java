package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;

@Mapper(componentModel = "spring")
public interface FilmMapperWithoutDependency extends DataMapperWithoutDependency<Film, FilmModel> {

    FilmMapperWithoutDependency INSTANCE = Mappers.getMapper(FilmMapperWithoutDependency.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "cinemas", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    Film toEntityWithoutDependency(FilmModel filmModel);

    @Override
    @Named("toModelWithoutDependency")
    @Mapping(target = "cinemas", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    FilmModel toModelWithoutDependency(Film film);
}
