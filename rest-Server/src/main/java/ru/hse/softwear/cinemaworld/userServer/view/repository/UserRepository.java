package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT * FROM users WHERE email = :email")
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("INSERT INTO users (email, password, name, number_phone)" +
           "values (:email, :password, :name, :numberPhone)")
    void save(String email, String password, String name, Long numberPhone);
}
