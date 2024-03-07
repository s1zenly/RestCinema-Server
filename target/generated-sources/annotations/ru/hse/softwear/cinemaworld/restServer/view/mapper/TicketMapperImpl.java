package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.restServer.view.model.TicketModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T20:44:18+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketModel toTicketModel(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketModel ticketModel = new TicketModel();

        ticketModel.setId( ticket.getId() );
        ticketModel.setName( ticket.getName() );
        ticketModel.setDate( ticket.getDate() );
        ticketModel.setPrice( ticket.getPrice() );

        return ticketModel;
    }

    @Override
    public Ticket toTicket(TicketModel ticketModel) {
        if ( ticketModel == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setId( ticketModel.getId() );
        ticket.setName( ticketModel.getName() );
        ticket.setDate( ticketModel.getDate() );
        ticket.setPrice( ticketModel.getPrice() );

        return ticket;
    }
}
