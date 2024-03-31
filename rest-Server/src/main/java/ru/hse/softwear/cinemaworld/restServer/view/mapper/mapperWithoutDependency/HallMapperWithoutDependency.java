package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces.DataMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.HallModel;

@Mapper(componentModel = "spring")
public interface HallMapperWithoutDependency extends DataMapperWithoutDependency<Hall, HallModel> {

    HallMapperWithoutDependency INSTANCE = Mappers.getMapper(HallMapperWithoutDependency.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "cinema", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    Hall toEntityWithoutDependency(HallModel hallModel);

    @Override
    @Named("toModelWithoutDependency")
    @Mapping(target = "cinema", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    HallModel toModelWithoutDependency(Hall hall);
}
