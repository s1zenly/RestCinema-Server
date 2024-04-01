package ru.hse.softwear.cinemaworld.authServer.service;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Admin;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Persona;
import ru.hse.softwear.cinemaworld.restServer.view.entity.User;
import ru.hse.softwear.cinemaworld.restServer.view.repository.AdminRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.PersonaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService {
    private final PersonaRepository personaRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public Optional<Persona> getByEmail(@NotNull String email) {
        return personaRepository.findByEmail(email);
    }

    public User getUser(@NotNull String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public Admin getAdmin(@NotNull String email) {
        return adminRepository.findByEmail(email).orElse(null);
    }
}
