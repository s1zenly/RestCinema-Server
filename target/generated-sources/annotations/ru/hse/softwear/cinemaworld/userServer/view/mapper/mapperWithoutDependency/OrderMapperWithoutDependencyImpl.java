package ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.OrderModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T12:51:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class OrderMapperWithoutDependencyImpl implements OrderMapperWithoutDependency {

    @Override
    public Order toEntityWithoutDependency(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderModel.getId() );
        order.setToken( orderModel.getToken() );

        return order;
    }

    @Override
    public OrderModel toModelWithoutDependency(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderModel orderModel = new OrderModel();

        orderModel.setId( order.getId() );
        orderModel.setToken( order.getToken() );

        return orderModel;
    }
}
