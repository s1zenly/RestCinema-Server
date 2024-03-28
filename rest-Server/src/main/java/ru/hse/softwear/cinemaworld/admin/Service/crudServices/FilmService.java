package ru.hse.softwear.cinemaworld.admin.Service.crudServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.admin.Service.CRUDservice;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.FilmMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.FilmRepository;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService implements CRUDservice<FilmModel, String> {

    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public void create(Object... objects) {
        String cinemaName = (String) objects[0];
        FilmModel filmDTO = (FilmModel) objects[1];

        Cinema cinema = cinemaRepository.findByName(cinemaName)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + cinemaName));

        Film film = FilmMapper.INSTANCE.toEntity(filmDTO);

        film.getCinemas().add(cinema);
        filmRepository.save(film);

        cinema.getFilms().add(film);
        cinemaRepository.save(cinema);
    }

    @Override
    public FilmModel read(String name) {
        Film film = filmRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Film not found with name: " + name));

        return FilmMapper.INSTANCE.toModel(film);
    }

    @Override
    public void update(String name, FilmModel filmDTO) {
        Film film = filmRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Film not found with name: " + name));

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
        String cinemaName = (String) objects[0];
        String filmName = (String) objects[1];

        Cinema cinema = cinemaRepository.findByName(cinemaName)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + cinemaName));

        Film film = filmRepository.findByName(filmName)
                .orElseThrow(() -> new NoSuchElementException("Film not found with name: " + filmName));

        cinema.getFilms().remove(film);
        film.getCinemas().remove(cinema);

        filmRepository.deleteById(filmName);
    }


    public List<FilmModel> getAll(String cinemaName) {

        return filmRepository.findAll().stream()
                .filter(film -> film.getCinemas().stream()
                        .anyMatch(cinema -> cinema.getName().equalsIgnoreCase(cinemaName)))
                .map(FilmMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }


}
