package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Custom;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.restServer.view.model.CustomModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.CustomerModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomMapper {
    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    CustomModel toCustomModel(Custom custom);
    Custom toCustom(CustomModel customModel);
}
