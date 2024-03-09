package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.CinemaMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.FilmMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.HallMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.TicketMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.TicketModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-10T01:23:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class SessionMapperImpl implements SessionMapper {

    @Autowired
    private CinemaMapperWithoutDependency cinemaMapperWithoutDependency;
    @Autowired
    private HallMapperWithoutDependency hallMapperWithoutDependency;
    @Autowired
    private FilmMapperWithoutDependency filmMapperWithoutDependency;
    @Autowired
    private TicketMapperWithoutDependency ticketMapperWithoutDependency;

    @Override
    public Session toEntity(SessionModel sessionModel) {
        if ( sessionModel == null ) {
            return null;
        }

        Session session = new Session();

        session.setHall( hallMapperWithoutDependency.toEntityWithoutDependency( sessionModel.getHall() ) );
        session.setCinema( cinemaMapperWithoutDependency.toEntityWithoutDependency( sessionModel.getCinema() ) );
        session.setFilm( filmMapperWithoutDependency.toEntityWithoutDependency( sessionModel.getFilm() ) );
        session.setTickets( ticketModelListToTicketList( sessionModel.getTickets() ) );
        session.setId( sessionModel.getId() );

        return session;
    }

    @Override
    public SessionModel toModel(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionModel sessionModel = new SessionModel();

        sessionModel.setHall( hallMapperWithoutDependency.toModelWithoutDependency( session.getHall() ) );
        sessionModel.setCinema( cinemaMapperWithoutDependency.toModelWithoutDependency( session.getCinema() ) );
        sessionModel.setFilm( filmMapperWithoutDependency.toModelWithoutDependency( session.getFilm() ) );
        sessionModel.setTickets( ticketListToTicketModelList( session.getTickets() ) );
        sessionModel.setId( session.getId() );

        return sessionModel;
    }

    protected List<Ticket> ticketModelListToTicketList(List<TicketModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Ticket> list1 = new ArrayList<Ticket>( list.size() );
        for ( TicketModel ticketModel : list ) {
            list1.add( ticketMapperWithoutDependency.toEntityWithoutDependency( ticketModel ) );
        }

        return list1;
    }

    protected List<TicketModel> ticketListToTicketModelList(List<Ticket> list) {
        if ( list == null ) {
            return null;
        }

        List<TicketModel> list1 = new ArrayList<TicketModel>( list.size() );
        for ( Ticket ticket : list ) {
            list1.add( ticketMapperWithoutDependency.toModelWithoutDependency( ticket ) );
        }

        return list1;
    }
}
