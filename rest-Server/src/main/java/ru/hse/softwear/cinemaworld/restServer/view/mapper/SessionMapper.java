package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    SessionModel toSessionModel(Session session);
    Session toSession(SessionModel sessionModel);
}
