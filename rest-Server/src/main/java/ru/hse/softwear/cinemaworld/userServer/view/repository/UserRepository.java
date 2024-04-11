package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query()
    Optional<User> findByEmail(String email);
}
