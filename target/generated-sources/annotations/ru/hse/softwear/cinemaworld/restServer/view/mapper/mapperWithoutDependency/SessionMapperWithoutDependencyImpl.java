package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-10T01:23:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class SessionMapperWithoutDependencyImpl implements SessionMapperWithoutDependency {

    @Override
    public Session toEntityWithoutDependency(SessionModel sessionModel) {
        if ( sessionModel == null ) {
            return null;
        }

        Session session = new Session();

        session.setId( sessionModel.getId() );

        return session;
    }

    @Override
    public SessionModel toModelWithoutDependency(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionModel sessionModel = new SessionModel();

        sessionModel.setId( session.getId() );

        return sessionModel;
    }
}
