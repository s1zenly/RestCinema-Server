package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

    @Query("SELECT * FROM sessions WHERE id = :id")
    Optional<Session> findById(Long id);

    @Query("DELETE FROM sessions WHERE id = :id")
    void deleteById(Long id);

    @Query("SELECT * FROM sessions")
    List<Session> findAll();

    @Query("INSERT INTO sessions (date, price, cinema_id, film_id, hall_id)" +
           "values (:date, :price, :cinemaId, :filmId, :hallId)")
    void save(Date date, Integer price, Long cinemaId, Long filmId, Long hallId);

    @Query("SELECT * FROM sessions WHERE film_id = :filmId && cinema_id =:cinemaId")
    List<Session> findAllByFilmIdAndCinemaId(Long filmId, Long cinemaId);

    @Query("SELECT * FROM sessions WHERE cinema_id = :cinemaId")
    List<Session> findAllByCinemaId(Long cinemaId);

    @Query("SELECT * FROM sessions WHERE film_id = :filmId")
    List<Session> findAllByFilmId(Long filmId);

    @Query("SELECT * FROM sessions WHERE hall_id = :hallId")
    List<Session> findAllByHallId(Long hallId);
}
