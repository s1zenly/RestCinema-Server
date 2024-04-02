package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.User;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.UserModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T03:46:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperWithoutDependencyImpl implements UserMapperWithoutDependency {

    @Override
    public User toEntityWithoutDependency(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        User user = new User();

        user.setId( userModel.getId() );
        user.setEmail( userModel.getEmail() );
        user.setPassword( userModel.getPassword() );
        user.setName( userModel.getName() );
        user.setNumberPhone( userModel.getNumberPhone() );

        return user;
    }

    @Override
    public UserModel toModelWithoutDependency(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( user.getId() );
        userModel.setEmail( user.getEmail() );
        userModel.setPassword( user.getPassword() );
        userModel.setName( user.getName() );
        userModel.setNumberPhone( user.getNumberPhone() );

        return userModel;
    }
}
