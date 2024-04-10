package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency.OrderMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.TicketModel;

@Mapper(componentModel = "spring",
        uses = {
                OrderMapperWithoutDependency.class,
                SessionMapperWithoutDependency.class
        })
public interface TicketMapper extends DataMapper<Ticket, TicketModel> {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Override
    @Mapping(target = "order", source = "order", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "session", source = "session", qualifiedByName = "toEntityWithoutDependency")
    Ticket toEntity(TicketModel ticketModel);

    @Override
    @Mapping(target = "order", source = "order", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "session", source = "session", qualifiedByName = "toModelWithoutDependency")
    TicketModel toModel(Ticket ticket);
}
