package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Persona;

import java.util.Optional;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, String> {

    @Query("SELECT * FROM personalities WHERE email = :email")
    Optional<Persona> findByEmail(String email);

    @Modifying
    @Query("INSERT INTO personalities (email, password, role)" +
           "values (:email, :password, :role)")
    void save(String email, String password, String role);
}
