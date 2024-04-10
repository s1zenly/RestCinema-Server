package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

@Mapper(componentModel = "spring")
public interface SessionMapperWithoutDependency extends DataMapperWithoutDependency<Session, SessionModel> {

    SessionMapperWithoutDependency INSTANCE = Mappers.getMapper(SessionMapperWithoutDependency.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "cinema", ignore = true)
    @Mapping(target = "film", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    Session toEntityWithoutDependency(SessionModel sessionModel);

    @Override
    @Named("toModelWithoutDependency")
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "cinema", ignore = true)
    @Mapping(target = "film", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    SessionModel toModelWithoutDependency(Session session);
}
