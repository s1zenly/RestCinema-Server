package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Persona;

import java.util.Optional;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, String> {
    Optional<Persona> findByEmail(String email);
}
