package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {

    @Query("SELECT * FROM cinemas WHERE id = :id")
    Optional<Cinema> findById(Long id);

    @Query("SELECT * FROM cinemas")
    List<Cinema> findAll();



    // Admin Only
    //---------------------------------------------------------------------------------------
    @Modifying
    @Query("DELETE FROM cinemas WHERE id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE cinemas SET info = :info, number_phone = :numberPhone, image = :image WHERE id = :id")
    void update(Long id, String info, Long numberPhone, String image);

    @Modifying
    @Query("INSERT INTO cinemas (name, latitude, longitude, info, number_phone, image)" +
           "values (:name, :latitude, :longitude, :info, :numberPhone, :image)")
    void save(String name, Double latitude, Double longitude, String info, Long numberPhone, String image);
}
