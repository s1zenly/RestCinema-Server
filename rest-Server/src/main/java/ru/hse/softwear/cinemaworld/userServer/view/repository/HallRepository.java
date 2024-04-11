package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;

import java.util.List;
import java.util.Optional;

@Repository
public interface HallRepository extends CrudRepository<Hall, Long> {

    @Query("SELECT * FROM halls WHERE id = :id")
    Optional<Hall> findById(Long id);
    @Query("SELECT * FROM halls")
    List<Hall> findAll();

    @Modifying
    @Query("DELETE FROM halls WHERE id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("INSERT INTO halls (name, rows, columns, cinema_id) values (:name, :rows, :columns, :cinemaId)")
    void save(String name, Integer rows, Integer columns, Long cinemaId);

    @Query("SELECT * FROM halls WHERE cinema_id = :cinemaId")
    List<Hall> findAllByCinemaId(Long cinemaId);
}
