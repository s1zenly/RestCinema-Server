package ru.hse.softwear.cinemaworld.restServer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.CinemaMapper;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.FilmMapper;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.SessionMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.FilmRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.SessionRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;

    public AbstractMap.SimpleEntry<FilmModel, Map<CinemaModel, List<SessionModel>>> getFilm(Long id, Date date) {
        FilmModel film = FilmMapper.INSTANCE.toModel(filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id: " + id)));

        List<SessionModel> sessions = film.getSessions().stream()
                .filter(session -> session.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                .toList();

        List<SessionModel> sessionList = sessions.stream()
                .map(sessionModel -> {
                    return sessionRepository.findById(sessionModel.getId())
                            .map(SessionMapper.INSTANCE::toModel)
                            .orElse(null);
                })
                .toList();

        Map<CinemaModel, List<SessionModel>> cinemasWithSessions = sessionList.stream()
                .collect(Collectors.groupingBy(SessionModel::getCinema));


        return new AbstractMap.SimpleEntry<>(film, cinemasWithSessions);
    }

    public String getTrailer(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found id: " + id));

        return film.getTrailerURL();
    }

}
