package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency.OrderMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.UserModel;

@Mapper(componentModel = "spring",
        uses = OrderMapperWithoutDependency.class)
public interface UserMapper extends DataMapper<User, UserModel> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    @Mapping(target = "orders", source = "orders", qualifiedByName = "toEntityWithoutDependency")
    User toEntity(UserModel userModel);

    @Override
    @Mapping(target = "orders", source = "orders", qualifiedByName = "toModelWithoutDependency")
    UserModel toModel(User user);
}
