package ru.hse.softwear.cinemaworld.userServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.CinemaMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.FilmMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.FilmWithSessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaFilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.SessionRepository;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaFilmRepository cinemaFilmRepository;
    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;

    public List<CinemaModel> getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();

        return cinemas.stream()
                .map(CinemaMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }


    public AbstractMap.SimpleEntry<CinemaModel, List<FilmWithSessionModel>> getCinema(Long id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with id: " + id));

        List<Long> filmsId = cinemaFilmRepository.findByCinemaId(id);

        List<FilmWithSessionModel> filmWithSession = filmsId.stream()
                .map(filmId -> {
                    Film film = filmRepository.findById(filmId).orElse(null);
                    FilmModel filmModel = FilmMapper.INSTANCE.toModel(film);

                    List<SessionModel> sessions = sessionRepository.findAllByFilmIdAndCinemaId(filmId, id).stream()
                            .map(SessionMapper.INSTANCE::toModel)
                            .toList();

                    if(sessions.isEmpty()) {
                        return null;
                    }

                    FilmWithSessionModel filmWithSessionModel = new FilmWithSessionModel();
                    filmWithSessionModel.setFilm(filmModel);
                    filmWithSessionModel.setSessions(sessions);
                    return filmWithSessionModel;

                })
                .filter(Objects::nonNull)
                .toList();

        return new AbstractMap.SimpleEntry<>(CinemaMapper.INSTANCE.toModel(cinema), filmWithSession);
    }
}
