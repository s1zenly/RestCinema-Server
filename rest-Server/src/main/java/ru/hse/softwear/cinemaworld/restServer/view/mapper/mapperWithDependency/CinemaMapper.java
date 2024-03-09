package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.FilmMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.HallMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;


@Mapper(componentModel = "spring",
        uses = {
                FilmMapperWithoutDependency.class,
                HallMapperWithoutDependency.class
        })
public interface CinemaMapper extends DataMapper<Cinema, CinemaModel> {

    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);

    @Override
    @Mapping(target = "films", source = "films", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "halls", source = "halls", qualifiedByName = "toEntityWithoutDependency")
    Cinema toEntity(CinemaModel cinemaModel);


    @Override
    @Mapping(target = "films", source = "films", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "halls", source = "halls", qualifiedByName = "toModelWithoutDependency")
    CinemaModel toModel(Cinema cinema);
}
