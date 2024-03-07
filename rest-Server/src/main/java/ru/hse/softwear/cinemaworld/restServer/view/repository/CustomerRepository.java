package ru.hse.softwear.cinemaworld.restServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
