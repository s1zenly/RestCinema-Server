package ru.hse.softwear.cinemaworld.adminServer.service.crudServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.service.CRUDservice;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.HallRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.SessionRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService implements CRUDservice<SessionModel, Long> {

    private final CinemaRepository cinemaRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;
    private final SessionRepository sessionRepository;

    @Override
    public void create(Object... objects) {
        Long cinemaId = (Long) objects[0];
        Long filmId = (Long) objects[1];
        Long hallId = (Long) objects[2];
        SessionModel sessionModel = (SessionModel) objects[3];

        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with id: " + cinemaId));

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + filmId));

        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + hallId));

        Session session = SessionMapper.INSTANCE.toEntity(sessionModel);
        session.setCinema(cinema);
        session.setFilm(film);
        session.setHall(hall);

        sessionRepository.save(session);
    }

    @Override
    public SessionModel read(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));

        return SessionMapper.INSTANCE.toModel(session);
    }

    @Override
    public void update(Long id, SessionModel sessionModel) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));

        // Mutable data
        session.setDate(Optional.ofNullable(sessionModel.getDate()).orElse(session.getDate()));
        session.setPrice(Optional.ofNullable(sessionModel.getPrice()).orElse(session.getPrice()));

        sessionRepository.save(session);
    }

    @Override
    public void delete(Object... objects) {
        Long id = (Long) objects[0];

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));

        session.setCinema(null);
        session.setFilm(null);
        session.setHall(null);
        session.setTickets(null);

        sessionRepository.save(session);
        sessionRepository.deleteById(id);
    }

    public List<SessionModel> getAll(Long cinemaId) {
        return sessionRepository.findAll().stream()
                .filter(session -> session.getCinema().getId().equals(cinemaId))
                .map(SessionMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }
}
