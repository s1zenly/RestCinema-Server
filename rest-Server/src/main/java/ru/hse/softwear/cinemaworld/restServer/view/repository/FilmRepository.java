package ru.hse.softwear.cinemaworld.restServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;

import java.util.Optional;

public interface FilmRepository extends  JpaRepository<Film, String> {

    Optional<Film> findByName(String s);
}
