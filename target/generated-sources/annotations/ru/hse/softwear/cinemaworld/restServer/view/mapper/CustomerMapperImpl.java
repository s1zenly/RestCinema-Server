package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Customer;
import ru.hse.softwear.cinemaworld.restServer.view.model.CustomerModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T20:44:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerModel toCustomerModel(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerModel customerModel = new CustomerModel();

        customerModel.setId( customer.getId() );
        customerModel.setEmail( customer.getEmail() );
        customerModel.setPassword( customer.getPassword() );

        return customerModel;
    }

    @Override
    public Customer toCustomer(CustomerModel customerModel) {
        if ( customerModel == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerModel.getId() );
        customer.setEmail( customerModel.getEmail() );
        customer.setPassword( customerModel.getPassword() );

        return customer;
    }
}
