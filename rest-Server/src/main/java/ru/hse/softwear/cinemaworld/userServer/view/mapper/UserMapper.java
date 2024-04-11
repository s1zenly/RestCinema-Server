package ru.hse.softwear.cinemaworld.userServer.view.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper extends DataMapper<User, UserModel> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    User toEntity(UserModel userModel);

    @Override
    UserModel toModel(User user);
}
