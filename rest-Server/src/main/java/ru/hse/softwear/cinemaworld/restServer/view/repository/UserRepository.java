package ru.hse.softwear.cinemaworld.restServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
