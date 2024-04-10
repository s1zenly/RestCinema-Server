package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Persona;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, String> {
    Optional<Persona> findByEmail(String email);
}
