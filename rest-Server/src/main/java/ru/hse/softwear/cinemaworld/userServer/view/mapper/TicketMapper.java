package ru.hse.softwear.cinemaworld.userServer.view.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.TicketModel;

@Mapper(componentModel = "spring")
public interface TicketMapper extends DataMapper<Ticket, TicketModel> {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Override
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "sessionId", ignore = true)
    Ticket toEntity(TicketModel ticketModel);

    @Override
    TicketModel toModel(Ticket ticket);
}
