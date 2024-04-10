package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;

@Mapper(componentModel = "spring")
public interface CinemaMapperWithoutDependency extends DataMapperWithoutDependency<Cinema, CinemaModel> {

    CinemaMapperWithoutDependency INSTANCE = Mappers.getMapper(CinemaMapperWithoutDependency.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "halls", ignore = true)
    Cinema toEntityWithoutDependency(CinemaModel cinemaModel);

    @Override
    @Named("toModelWithoutDependency")
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "halls", ignore = true)
    CinemaModel toModelWithoutDependency(Cinema cinema);
}
