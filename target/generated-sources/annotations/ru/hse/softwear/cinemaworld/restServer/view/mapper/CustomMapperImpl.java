package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Custom;
import ru.hse.softwear.cinemaworld.restServer.view.model.CustomModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T20:44:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class CustomMapperImpl implements CustomMapper {

    @Override
    public CustomModel toCustomModel(Custom custom) {
        if ( custom == null ) {
            return null;
        }

        CustomModel customModel = new CustomModel();

        customModel.setId( custom.getId() );

        return customModel;
    }

    @Override
    public Custom toCustom(CustomModel customModel) {
        if ( customModel == null ) {
            return null;
        }

        Custom custom = new Custom();

        custom.setId( customModel.getId() );

        return custom;
    }
}
