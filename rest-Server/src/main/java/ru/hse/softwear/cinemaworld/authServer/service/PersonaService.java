package ru.hse.softwear.cinemaworld.authServer.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.cypher.PersonPasswordCypher;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Admin;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Persona;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.repository.AdminRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.PersonaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.UserRepository;

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

    public void saveUser(String email, String password) {
        Persona persona = new Persona();
        User user = new User();
        String hashedPassword = PersonPasswordCypher.PasswordHashing(password);

        user.setEmail(email);
        user.setPassword(hashedPassword);

        persona.setEmail(email);
        persona.setPassword(hashedPassword);

        personaRepository.save(persona.getEmail(), persona.getPassword(), persona.getRole().getValue());
        userRepository.save(user.getEmail(), user.getPassword(), user.getName(), user.getNumberPhone());
    }

    public Admin getAdmin(@NotNull String email) {
        return adminRepository.findByEmail(email).orElse(null);
    }
}
