package ru.hse.softwear.cinemaworld.restServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String> {
    Cinema findByName(String name);
}
