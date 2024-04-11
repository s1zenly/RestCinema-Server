package ru.hse.softwear.cinemaworld.userServer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.CinemaMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.FilmMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfoService {

    private final CinemaRepository cinemaRepository;
    private final FilmRepository filmRepository;

    // Main page
    public List<FilmModel> getAllFilm() {
        return filmRepository.findAll().stream()
                .map(FilmMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }

    public List<CinemaModel> getAllCinema() {
        return cinemaRepository.findAll().stream()
                .map(CinemaMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }

    // Current films
    public List<FilmModel> getCurrentFilm() {
        return filmRepository.findAll().stream()
                .filter(Film::getCurrent)
                .map(FilmMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }

    // Soon films
    public List<FilmModel> getSoonFilms() {
        return filmRepository.findAll().stream()
                .filter(film -> !film.getCurrent())
                .map(FilmMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }
}
