package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.TicketModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T12:51:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class TicketMapperWithoutDependencyImpl implements TicketMapperWithoutDependency {

    @Override
    public Ticket toEntityWithoutDependency(TicketModel ticketModel) {
        if ( ticketModel == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setId( ticketModel.getId() );
        ticket.setSection( ticketModel.getSection() );
        ticket.setSubsection( ticketModel.getSubsection() );

        return ticket;
    }

    @Override
    public TicketModel toModelWithoutDependency(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketModel ticketModel = new TicketModel();

        ticketModel.setId( ticket.getId() );
        ticketModel.setSection( ticket.getSection() );
        ticketModel.setSubsection( ticket.getSubsection() );

        return ticketModel;
    }
}
