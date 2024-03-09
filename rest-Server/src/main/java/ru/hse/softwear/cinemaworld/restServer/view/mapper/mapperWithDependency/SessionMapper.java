package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.CinemaMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.FilmMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.HallMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.TicketMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;

@Mapper(componentModel = "spring",
        uses = {
                CinemaMapperWithoutDependency.class,
                HallMapperWithoutDependency.class,
                FilmMapperWithoutDependency.class,
                TicketMapperWithoutDependency.class
        })
public interface SessionMapper extends DataMapper<Session, SessionModel> {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    @Override
    @Mapping(target = "hall", source = "hall", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "cinema", source = "cinema", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "film", source = "film", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "tickets", source = "tickets", qualifiedByName = "toEntityWithoutDependency")
    Session toEntity(SessionModel sessionModel);

    @Override
    @Mapping(target = "hall", source = "hall", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "cinema", source = "cinema", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "film", source = "film", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "tickets", source = "tickets", qualifiedByName = "toModelWithoutDependency")
    SessionModel toModel(Session session);
}
