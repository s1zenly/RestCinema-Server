package ru.hse.softwear.cinemaworld.admin.service.crudServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.admin.service.CRUDservice;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.FilmMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.FilmRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceAdmin implements CRUDservice<FilmModel, Long> {

    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public void create(Object... objects) {
        Long cinemaId = (Long) objects[0];
        FilmModel filmDTO = (FilmModel) objects[1];

        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + cinemaId));

        Film film = FilmMapper.INSTANCE.toEntity(filmDTO);

        film.getCinemas().add(cinema);
        filmRepository.save(film);

        cinema.getFilms().add(film);
        cinemaRepository.save(cinema);
    }

    @Override
    public FilmModel read(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found with name: " + id));

        return FilmMapper.INSTANCE.toModel(film);
    }

    @Override
    public void update(Long id, FilmModel filmDTO) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found with name: " + id));

        // Mutable data
        film.setInfo(Optional.ofNullable(filmDTO.getInfo()).orElse(film.getInfo()));
        film.setYear(Optional.ofNullable(filmDTO.getYear()).orElse(film.getYear()));
        film.setDuration(Optional.ofNullable(filmDTO.getDuration()).orElse(film.getDuration()));
        film.setCurrent(Optional.ofNullable(filmDTO.getCurrent()).orElse(film.getCurrent()));
        film.setActors(Optional.ofNullable(filmDTO.getActors()).orElse(film.getActors()));
        film.setImage(Optional.ofNullable(filmDTO.getImage()).orElse(film.getImage()));
        film.setProducer(Optional.ofNullable(filmDTO.getProducer()).orElse(film.getProducer()));
        film.setAgeCategory(Optional.ofNullable(filmDTO.getAgeCategory()).orElse(film.getAgeCategory()));
        film.setProductionCountry(Optional.ofNullable(filmDTO.getProductionCountry()).orElse(film.getProductionCountry()));
        film.setTrailerURL(Optional.ofNullable(filmDTO.getTrailerURL()).orElse(film.getTrailerURL()));

        filmRepository.saveAndFlush(film);
    }

    @Override
    public void delete(Object... objects) {
        Long cinemaId = (Long) objects[0];
        Long filmId = (Long) objects[1];

        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + cinemaId));

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new NoSuchElementException("Film not found with name: " + filmId));

        cinema.getFilms().remove(film);
        film.getCinemas().remove(cinema);

        filmRepository.deleteById(filmId);
    }


    public List<FilmModel> getAll(Long cinemaId) {

        return filmRepository.findAll().stream()
                .filter(film -> film.getCinemas().stream()
                        .anyMatch(cinema -> cinema.getId().equals(cinemaId)))
                .map(FilmMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }


}
