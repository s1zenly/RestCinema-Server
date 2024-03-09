package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces.DataMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.OrderModel;

@Mapper(componentModel = "spring")
public interface OrderMapperWithoutDependency extends DataMapperWithoutDependency<Order, OrderModel> {

    OrderMapperWithoutDependency INSTANCE = Mappers.getMapper(OrderMapperWithoutDependency.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    Order toEntityWithoutDependency(OrderModel orderModel);

    @Override
    @Named("toModelWithoutDependency")
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    OrderModel toModelWithoutDependency(Order order);
}
