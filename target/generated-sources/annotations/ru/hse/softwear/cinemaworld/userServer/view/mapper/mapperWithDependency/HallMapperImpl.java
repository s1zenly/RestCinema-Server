package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency.CinemaMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.HallModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T12:51:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class HallMapperImpl implements HallMapper {

    @Autowired
    private CinemaMapperWithoutDependency cinemaMapperWithoutDependency;
    @Autowired
    private SessionMapperWithoutDependency sessionMapperWithoutDependency;

    @Override
    public Hall toEntity(HallModel hallModel) {
        if ( hallModel == null ) {
            return null;
        }

        Hall hall = new Hall();

        hall.setCinema( cinemaMapperWithoutDependency.toEntityWithoutDependency( hallModel.getCinema() ) );
        hall.setSessions( sessionModelListToSessionList( hallModel.getSessions() ) );
        hall.setId( hallModel.getId() );
        hall.setName( hallModel.getName() );
        hall.setRows( hallModel.getRows() );
        hall.setColumns( hallModel.getColumns() );

        return hall;
    }

    @Override
    public HallModel toModel(Hall hall) {
        if ( hall == null ) {
            return null;
        }

        HallModel hallModel = new HallModel();

        hallModel.setCinema( cinemaMapperWithoutDependency.toModelWithoutDependency( hall.getCinema() ) );
        hallModel.setSessions( sessionListToSessionModelList( hall.getSessions() ) );
        hallModel.setId( hall.getId() );
        hallModel.setName( hall.getName() );
        hallModel.setRows( hall.getRows() );
        hallModel.setColumns( hall.getColumns() );

        return hallModel;
    }

    protected List<Session> sessionModelListToSessionList(List<SessionModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Session> list1 = new ArrayList<Session>( list.size() );
        for ( SessionModel sessionModel : list ) {
            list1.add( sessionMapperWithoutDependency.toEntityWithoutDependency( sessionModel ) );
        }

        return list1;
    }

    protected List<SessionModel> sessionListToSessionModelList(List<Session> list) {
        if ( list == null ) {
            return null;
        }

        List<SessionModel> list1 = new ArrayList<SessionModel>( list.size() );
        for ( Session session : list ) {
            list1.add( sessionMapperWithoutDependency.toModelWithoutDependency( session ) );
        }

        return list1;
    }
}
