package ru.hse.softwear.cinemaworld.restServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;

import java.util.Optional;

public interface FilmRepository extends  JpaRepository<Film, Long> {

    Optional<Film> findById(Long id);
    Optional<Film> findByName(String s);
}
