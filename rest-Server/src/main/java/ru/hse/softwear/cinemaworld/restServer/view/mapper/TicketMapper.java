package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.restServer.view.model.TicketModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketModel toTicketModel(Ticket ticket);
    Ticket toTicket(TicketModel ticketModel);
}
