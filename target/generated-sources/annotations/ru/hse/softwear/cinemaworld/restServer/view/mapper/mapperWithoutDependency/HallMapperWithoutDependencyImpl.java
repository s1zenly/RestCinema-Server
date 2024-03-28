package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.restServer.view.model.HallModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-28T00:51:28+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class HallMapperWithoutDependencyImpl implements HallMapperWithoutDependency {

    @Override
    public Hall toEntityWithoutDependency(HallModel hallModel) {
        if ( hallModel == null ) {
            return null;
        }

        Hall hall = new Hall();

        hall.setId( hallModel.getId() );
        hall.setName( hallModel.getName() );
        hall.setRows( hallModel.getRows() );
        hall.setColumns( hallModel.getColumns() );

        return hall;
    }

    @Override
    public HallModel toModelWithoutDependency(Hall hall) {
        if ( hall == null ) {
            return null;
        }

        HallModel hallModel = new HallModel();

        hallModel.setId( hall.getId() );
        hallModel.setName( hall.getName() );
        hallModel.setRows( hall.getRows() );
        hallModel.setColumns( hall.getColumns() );

        return hallModel;
    }
}
