package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapperWithoutDependency extends DataMapperWithoutDependency<User, UserModel> {

    UserMapperWithoutDependency INSTANCE = Mappers.getMapper(UserMapperWithoutDependency.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "orders", ignore = true)
    User toEntityWithoutDependency(UserModel userModel);

    @Override
    @Named("toModelWithoutDependency")
    @Mapping(target = "orders", ignore = true)
    UserModel toModelWithoutDependency(User user);
}
