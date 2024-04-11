package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.enums.AgeCategories;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

    @Query("SELECT * FROM films WHERE id = :id")
    Optional<Film> findById(Long id);

    @Query("SELECT * FROM films")
    List<Film> findAll();

    @Modifying
    @Query("INSERT INTO films (name, year, producer, duration, actors, trailer, info, current, image, age_category, production_country) " +
           "values (:name, :year, :producer, :duration, :actors, :trailer, :info, :current, :image, :ageCategory::age_category_enum, :productionCountry)")
    void save(String name, Integer year, String producer, Duration duration, String actors,
              String trailer, String info, Boolean current, String image, String ageCategory, String productionCountry);

    @Query("SELECT * FROM films WHERE name = :name")
    Optional<Film> findByName(String name);

    @Modifying
    @Query("DELETE FROM films WHERE id = :id")
    void deleteById(Long id);
}
