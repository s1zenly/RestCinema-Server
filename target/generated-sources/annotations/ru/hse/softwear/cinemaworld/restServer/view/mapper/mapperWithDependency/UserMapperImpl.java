package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.restServer.view.entity.User;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.OrderMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.OrderModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.UserModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T03:46:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private OrderMapperWithoutDependency orderMapperWithoutDependency;

    @Override
    public User toEntity(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        User user = new User();

        user.setOrders( orderModelListToOrderList( userModel.getOrders() ) );
        user.setId( userModel.getId() );
        user.setEmail( userModel.getEmail() );
        user.setPassword( userModel.getPassword() );
        user.setName( userModel.getName() );
        user.setNumberPhone( userModel.getNumberPhone() );

        return user;
    }

    @Override
    public UserModel toModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setOrders( orderListToOrderModelList( user.getOrders() ) );
        userModel.setId( user.getId() );
        userModel.setEmail( user.getEmail() );
        userModel.setPassword( user.getPassword() );
        userModel.setName( user.getName() );
        userModel.setNumberPhone( user.getNumberPhone() );

        return userModel;
    }

    protected List<Order> orderModelListToOrderList(List<OrderModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Order> list1 = new ArrayList<Order>( list.size() );
        for ( OrderModel orderModel : list ) {
            list1.add( orderMapperWithoutDependency.toEntityWithoutDependency( orderModel ) );
        }

        return list1;
    }

    protected List<OrderModel> orderListToOrderModelList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderModel> list1 = new ArrayList<OrderModel>( list.size() );
        for ( Order order : list ) {
            list1.add( orderMapperWithoutDependency.toModelWithoutDependency( order ) );
        }

        return list1;
    }
}
