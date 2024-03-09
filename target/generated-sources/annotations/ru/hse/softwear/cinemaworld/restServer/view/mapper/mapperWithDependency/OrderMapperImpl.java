package ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.SessionMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.TicketMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.UserMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.model.OrderModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.TicketModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-10T01:23:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private SessionMapperWithoutDependency sessionMapperWithoutDependency;
    @Autowired
    private UserMapperWithoutDependency userMapperWithoutDependency;
    @Autowired
    private TicketMapperWithoutDependency ticketMapperWithoutDependency;

    @Override
    public Order toEntity(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }

        Order order = new Order();

        order.setSession( sessionMapperWithoutDependency.toEntityWithoutDependency( orderModel.getSession() ) );
        order.setUser( userMapperWithoutDependency.toEntityWithoutDependency( orderModel.getUser() ) );
        order.setTickets( ticketModelListToTicketList( orderModel.getTickets() ) );
        order.setId( orderModel.getId() );

        return order;
    }

    @Override
    public OrderModel toModel(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderModel orderModel = new OrderModel();

        orderModel.setSession( sessionMapperWithoutDependency.toModelWithoutDependency( order.getSession() ) );
        orderModel.setUser( userMapperWithoutDependency.toModelWithoutDependency( order.getUser() ) );
        orderModel.setTickets( ticketListToTicketModelList( order.getTickets() ) );
        orderModel.setId( order.getId() );

        return orderModel;
    }

    protected List<Ticket> ticketModelListToTicketList(List<TicketModel> list) {
        if ( list == null ) {
            return null;
        }

        List<Ticket> list1 = new ArrayList<Ticket>( list.size() );
        for ( TicketModel ticketModel : list ) {
            list1.add( ticketMapperWithoutDependency.toEntityWithoutDependency( ticketModel ) );
        }

        return list1;
    }

    protected List<TicketModel> ticketListToTicketModelList(List<Ticket> list) {
        if ( list == null ) {
            return null;
        }

        List<TicketModel> list1 = new ArrayList<TicketModel>( list.size() );
        for ( Ticket ticket : list ) {
            list1.add( ticketMapperWithoutDependency.toModelWithoutDependency( ticket ) );
        }

        return list1;
    }
}
