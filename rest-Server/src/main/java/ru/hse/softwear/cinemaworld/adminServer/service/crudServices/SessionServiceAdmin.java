package ru.hse.softwear.cinemaworld.adminServer.service.crudServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.service.CRUDservice;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.HallRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.SessionRepository;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionServiceAdmin implements CRUDservice<SessionModel, Long> {

    private final SessionRepository sessionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    @Override
    public void create(Object... objects) {
        Long cinemaId = (Long) objects[0];
        Long filmId = (Long) objects[1];
        Long hallId = (Long) objects[2];
        SessionModel sessionModel = (SessionModel) objects[3];

        Session session = SessionMapper.INSTANCE.toEntity(sessionModel);
        session.setCinemaId(cinemaId);
        session.setFilmId(filmId);
        session.setHallId(hallId);

        sessionRepository.save(session.getDate(), session.getPrice(), session.getCinemaId(),
                session.getFilmId(), session.getHallId());
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

        sessionRepository.save(session.getDate(), session.getPrice(), session.getCinemaId(),
                session.getFilmId(), session.getHallId());
    }

    @Override
    public void delete(Object... objects) {
        Long id = (Long) objects[0];

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
