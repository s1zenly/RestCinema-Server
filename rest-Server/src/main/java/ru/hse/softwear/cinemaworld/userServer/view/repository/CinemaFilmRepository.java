package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.CinemaFilm;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaFilmRepository extends CrudRepository<CinemaFilm, Long> {

    @Query("SELECT * FROM cinemas_films WHERE cinema_id = :cinemaId AND film_id = :filmId")
    Optional<Object> alreadyExists(Long cinemaId, Long filmId);

    @Query("SELECT film_id FROM cinemas_films WHERE cinema_id = :cinemaId")
    List<Long> findByCinemaId(Long cinemaId);

    @Query("SELECT cinema_id FROM cinemas_films WHERE film_id = :filmId")
    List<Long> findByFilmId(Long filmId);

    @Modifying
    @Query("INSERT INTO cinemas_films (cinema_id, film_id) values (:cinemaId, :filmId)")
    void save(Long cinemaId, Long filmId);

    @Modifying
    @Query("DELETE FROM cinemas_films WHERE cinema_id = :cinemaId AND film_id = :filmId")
    void delete(Long cinemaId, Long filmId);
}
