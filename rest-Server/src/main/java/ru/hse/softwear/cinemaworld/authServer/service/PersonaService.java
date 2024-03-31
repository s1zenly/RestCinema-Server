package ru.hse.softwear.cinemaworld.authServer.service;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Persona;
import ru.hse.softwear.cinemaworld.restServer.view.repository.PersonaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService {
    private final PersonaRepository personaRepository;

    public Optional<Persona> getByEmail(@NotNull String email) {
        return personaRepository.findByEmail(email);
    }
}
