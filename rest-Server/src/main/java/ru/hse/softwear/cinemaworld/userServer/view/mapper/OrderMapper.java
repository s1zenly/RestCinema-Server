package ru.hse.softwear.cinemaworld.userServer.view.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.OrderModel;

@Mapper(componentModel = "spring")
public interface OrderMapper extends DataMapper<Order, OrderModel> {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Override
    @Mapping(target = "sessionId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Order toEntity(OrderModel orderModel);

    @Override
    OrderModel toModel(Order order);
}
