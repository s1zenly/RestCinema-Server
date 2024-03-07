package ru.hse.softwear.cinemaworld.restServer.view.mapper;


import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.restServer.view.model.HallModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HallMapper {
    HallMapper INSTANCE = Mappers.getMapper(HallMapper.class);

    HallModel toHallModel(Hall hall);
    Hall toHall(HallModel hallModel);
}
