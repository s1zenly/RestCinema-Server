package ru.hse.softwear.cinemaworld.adminServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.view.model.SessionUpdateModel;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.HallRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.SessionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionServiceAdmin {

    private final SessionRepository sessionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    public void create(Long cinemaId, Long filmId, Long hallId, SessionModel sessionModel) {
        Session session = SessionMapper.INSTANCE.toEntity(sessionModel);
        session.setCinemaId(cinemaId);
        session.setFilmId(filmId);
        session.setHallId(hallId);

        sessionRepository.save(session.getDate(), session.getPrice(), session.getCinemaId(),
                session.getFilmId(), session.getHallId());
    }

    public SessionModel read(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));

        return SessionMapper.INSTANCE.toModel(session);
    }

    public void update(Long id, SessionUpdateModel changes) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));

        // Mutable data
        Date date = Optional.ofNullable(changes.getDate()).orElse(session.getDate());
        Integer price = Optional.ofNullable(changes.getPrice()).orElse(session.getPrice());

        sessionRepository.update(id, date, price);
    }

    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }

    public List<SessionModel> getAll(Long cinemaId) {
        return sessionRepository.findAll().stream()
                .filter(session -> session.getCinemaId().equals(cinemaId))
                .map(SessionMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }

    public AbstractMap.SimpleEntry<String, String> getSessionInfo(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));
        Film film = filmRepository.findById(session.getFilmId())
                .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + session.getFilmId()));
        Hall hall = hallRepository.findById(session.getHallId())
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + session.getHallId()));

        return new AbstractMap.SimpleEntry<>(film.getName(), hall.getName());
    }
}
