package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T12:51:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
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
        session.setDate( sessionModel.getDate() );
        session.setPrice( sessionModel.getPrice() );

        return session;
    }

    @Override
    public SessionModel toModelWithoutDependency(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionModel sessionModel = new SessionModel();

        sessionModel.setId( session.getId() );
        sessionModel.setDate( session.getDate() );
        sessionModel.setPrice( session.getPrice() );

        return sessionModel;
    }
}
