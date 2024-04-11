package ru.hse.softwear.cinemaworld.userServer.view.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;


@Mapper(componentModel = "spring")
public interface FilmMapper extends DataMapper<Film, FilmModel> {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Override
    Film toEntity(FilmModel filmModel);

    @Override
    FilmModel toModel(Film film);
}
