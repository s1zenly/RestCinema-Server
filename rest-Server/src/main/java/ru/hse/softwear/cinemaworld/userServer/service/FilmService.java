package ru.hse.softwear.cinemaworld.userServer.service;

import jakarta.persistence.EntityNotFoundException;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithoutDependency.FilmMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.SessionRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;

    public AbstractMap.SimpleEntry<FilmModel, Map<CinemaModel, List<SessionModel>>> getFilm(Long id, Date date) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id: " + id));

        List<Session> sessions = film.getSessions().stream()
                .filter(session -> session.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                .toList();

        List<SessionModel> sessionModels = sessions.stream()
                .map(sessionModel -> {
                    return sessionRepository.findById(sessionModel.getId())
                            .map(SessionMapper.INSTANCE::toModel)
                            .orElse(null);
                })
                .toList();

        Map<CinemaModel, List<SessionModel>> cinemasWithSessions = sessionModels.stream()
                .collect(Collectors.groupingBy(SessionModel::getCinema));

        return new AbstractMap.SimpleEntry<>(FilmMapperWithoutDependency.INSTANCE.toModelWithoutDependency(film), cinemasWithSessions);
    }

    public String getTrailer(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found id: " + id));

        return film.getTrailerURL();
    }

}
