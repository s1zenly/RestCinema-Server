package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T20:44:18+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class SessionMapperImpl implements SessionMapper {

    @Override
    public SessionModel toSessionModel(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionModel sessionModel = new SessionModel();

        sessionModel.setId( session.getId() );

        return sessionModel;
    }

    @Override
    public Session toSession(SessionModel sessionModel) {
        if ( sessionModel == null ) {
            return null;
        }

        Session session = new Session();

        session.setId( sessionModel.getId() );

        return session;
    }
}
