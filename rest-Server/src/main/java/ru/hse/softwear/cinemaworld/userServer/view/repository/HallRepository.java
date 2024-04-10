package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
