package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;


import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.CinemaMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.HallModel;

@Mapper(componentModel = "spring",
        uses = {
                CinemaMapperWithoutDependency.class,
                SessionMapperWithoutDependency.class
        })
public interface HallMapper extends DataMapper<Hall, HallModel> {

    HallMapper INSTANCE = Mappers.getMapper(HallMapper.class);

    @Override
    @Mapping(target = "cinema", source = "cinema", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "sessions", source = "sessions", qualifiedByName = "toEntityWithoutDependency")
    Hall toEntity(HallModel hallModel);

    @Override
    @Mapping(target = "cinema", source = "cinema", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "sessions", source = "sessions", qualifiedByName = "toModelWithoutDependency")
    HallModel toModel(Hall hall);
}
