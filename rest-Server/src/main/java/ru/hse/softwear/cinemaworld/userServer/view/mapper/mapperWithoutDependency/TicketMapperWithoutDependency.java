package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.TicketModel;

@Mapper(componentModel = "spring")
public interface TicketMapperWithoutDependency extends DataMapperWithoutDependency<Ticket, TicketModel> {

    TicketMapperWithoutDependency INSTANCE = Mappers.getMapper(TicketMapperWithoutDependency.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "session", ignore = true)
    Ticket toEntityWithoutDependency(TicketModel ticketModel);

    @Override
    @Named("toModelWithoutDependency")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "session", ignore = true)
    TicketModel toModelWithoutDependency(Ticket ticket);
}
