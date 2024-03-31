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

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public AbstractMap.SimpleEntry<FilmModel, Map<CinemaModel, List<SessionModel>>> getFilm(Long id, Date date) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id: " + id));

        List<Session> sessions = film.getSessions().stream()
                .filter(session -> session.getDate().equals(date))
                .toList();

        Map<Cinema, List<Session>> cinemasWithSessions = sessions.stream()
                .collect(Collectors.groupingBy(Session::getCinema));

        Map<CinemaModel, List<SessionModel>> cinemasWithSessionsModel = cinemasWithSessions.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> CinemaMapper.INSTANCE.toModel(entry.getKey()),
                        entry -> entry.getValue().stream()
                                .map(SessionMapper.INSTANCE::toModel)
                                .collect(Collectors.toList())
                ));

        return new AbstractMap.SimpleEntry<>(FilmMapper.INSTANCE.toModel(film), cinemasWithSessionsModel);
    }

    public String getTrailer(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found id: " + id));

        return film.getTrailerURL();
    }

}
