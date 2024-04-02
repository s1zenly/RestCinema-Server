package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.OrderMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.TicketModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T10:53:20+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    @Autowired
    private OrderMapperWithoutDependency orderMapperWithoutDependency;
    @Autowired
    private SessionMapperWithoutDependency sessionMapperWithoutDependency;

    @Override
    public Ticket toEntity(TicketModel ticketModel) {
        if ( ticketModel == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setOrder( orderMapperWithoutDependency.toEntityWithoutDependency( ticketModel.getOrder() ) );
        ticket.setSession( sessionMapperWithoutDependency.toEntityWithoutDependency( ticketModel.getSession() ) );
        ticket.setId( ticketModel.getId() );
        ticket.setSection( ticketModel.getSection() );
        ticket.setSubsection( ticketModel.getSubsection() );

        return ticket;
    }

    @Override
    public TicketModel toModel(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketModel ticketModel = new TicketModel();

        ticketModel.setOrder( orderMapperWithoutDependency.toModelWithoutDependency( ticket.getOrder() ) );
        ticketModel.setSession( sessionMapperWithoutDependency.toModelWithoutDependency( ticket.getSession() ) );
        ticketModel.setId( ticket.getId() );
        ticketModel.setSection( ticket.getSection() );
        ticketModel.setSubsection( ticket.getSubsection() );

        return ticketModel;
    }
}
