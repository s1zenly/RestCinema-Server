package ru.hse.softwear.cinemaworld.authServer.service;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

        user.setEmail(email);
        user.setPassword(password);

        persona.setEmail(email);
        persona.setPassword(password);

        personaRepository.save(persona);
        userRepository.save(user);
    }

    public Admin getAdmin(@NotNull String email) {
        return adminRepository.findByEmail(email).orElse(null);
    }
}
