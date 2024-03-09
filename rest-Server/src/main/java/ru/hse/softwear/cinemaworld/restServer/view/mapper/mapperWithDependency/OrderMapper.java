package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces.DataMapper;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.TicketMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.UserMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.OrderModel;

@Mapper(componentModel = "spring",
        uses = {
                SessionMapperWithoutDependency.class,
                UserMapperWithoutDependency.class,
                TicketMapperWithoutDependency.class
        })
public interface OrderMapper extends DataMapper<Order, OrderModel> {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Override
    @Mapping(target = "session", source = "session", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "user", source = "user", qualifiedByName = "toEntityWithoutDependency")
    @Mapping(target = "tickets", source = "tickets", qualifiedByName = "toEntityWithoutDependency")
    Order toEntity(OrderModel orderModel);

    @Override
    @Mapping(target = "session", source = "session", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "user", source = "user", qualifiedByName = "toModelWithoutDependency")
    @Mapping(target = "tickets", source = "tickets", qualifiedByName = "toModelWithoutDependency")
    OrderModel toModel(Order order);
}
