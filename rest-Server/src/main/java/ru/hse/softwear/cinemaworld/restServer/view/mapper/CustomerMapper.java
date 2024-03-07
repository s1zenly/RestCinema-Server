package ru.hse.softwear.cinemaworld.restServer.view.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Customer;
import ru.hse.softwear.cinemaworld.restServer.view.model.CustomerModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerModel toCustomerModel(Customer customer);
    Customer toCustomer(CustomerModel customerModel);
}
