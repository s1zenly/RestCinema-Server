package ru.hse.softwear.cinemaworld.userServer.view.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;


@Mapper(componentModel = "spring")
public interface CinemaMapper extends DataMapper<Cinema, CinemaModel> {

    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);

    @Override
    Cinema toEntity(CinemaModel cinemaModel);

    @Override
    CinemaModel toModel(Cinema cinema);
}
