package ru.hse.softwear.cinemaworld.userServer.view.mapper;


import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.HallModel;

@Mapper(componentModel = "spring")
public interface HallMapper extends DataMapper<Hall, HallModel> {

    HallMapper INSTANCE = Mappers.getMapper(HallMapper.class);

    @Override
    @Mapping(target = "cinemaId", ignore = true)
    Hall toEntity(HallModel hallModel);

    @Override

    HallModel toModel(Hall hall);
}
