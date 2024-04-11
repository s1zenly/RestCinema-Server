package ru.hse.softwear.cinemaworld.userServer.view.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

@Mapper(componentModel = "spring")
public interface SessionMapper extends DataMapper<Session, SessionModel> {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    @Override
    @Mapping(target = "cinemaId", ignore = true)
    @Mapping(target = "hallId", ignore = true)
    @Mapping(target = "filmId", ignore = true)
    Session toEntity(SessionModel sessionModel);

    @Override
    SessionModel toModel(Session session);
}
