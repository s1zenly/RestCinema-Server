package ru.hse.softwear.cinemaworld.restServer.view.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.model.Film;

public interface PosterRepository extends  JpaRepository<Film, Long> {

}
