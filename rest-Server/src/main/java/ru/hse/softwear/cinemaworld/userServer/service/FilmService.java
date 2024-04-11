package ru.hse.softwear.cinemaworld.userServer.service;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.CinemaMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.FilmMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.CinemaWithSessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaFilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.SessionRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final SessionRepository sessionRepository;
    private final CinemaRepository cinemaRepository;
    private final CinemaFilmRepository cinemaFilmRepository;

    public AbstractMap.SimpleEntry<FilmModel, List<CinemaWithSessionModel>> getFilm(Long id, Date date) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + id));

        List<Long> cinemasId = cinemaFilmRepository.findByFilmId(id);

        List<CinemaWithSessionModel> cinemasWithSessions = cinemasId.stream()
                .map(cinemaId -> {
                    Cinema cinema = cinemaRepository.findById(cinemaId).orElse(null);
                    CinemaModel cinemaModel = CinemaMapper.INSTANCE.toModel(cinema);

                    List<SessionModel> sessions = sessionRepository.findAllByFilmIdAndCinemaId(id, cinemaId).stream()
                            .map(SessionMapper.INSTANCE::toModel)
                            .toList();

                    if(sessions.isEmpty()) {
                        return null;
                    }

                    CinemaWithSessionModel cinemaWithSessionModel = new CinemaWithSessionModel();
                    cinemaWithSessionModel.setCinema(cinemaModel);
                    cinemaWithSessionModel.setSessions(sessions);

                    return cinemaWithSessionModel;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new AbstractMap.SimpleEntry<>(FilmMapper.INSTANCE.toModel(film), cinemasWithSessions);
    }

    public String getTrailer(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found id: " + id));

        return film.getTrailer();
    }

}
