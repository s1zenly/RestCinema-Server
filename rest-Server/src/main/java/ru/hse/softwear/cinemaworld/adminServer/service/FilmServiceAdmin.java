package ru.hse.softwear.cinemaworld.adminServer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Film;
import ru.hse.softwear.cinemaworld.userServer.view.enums.AgeCategories;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.FilmMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaFilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceAdmin {

    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;
    private final CinemaFilmRepository cinemaFilmRepository;

    public void create(Long cinemaId, FilmModel filmModel) {
        Film film = FilmMapper.INSTANCE.toEntity(filmModel);

        filmRepository.save(film.getName(), film.getYear(), film.getProducer(),
                film.getDuration(), film.getActors(), film.getTrailer(), film.getInfo(),
                film.getCurrent(), film.getImage(), Optional.ofNullable(film.getAgeCategory()).map(AgeCategories::getValue).orElse(null), film.getProductionCountry());

        //cinemaFilmRepository.save(cinemaId, filmModel.getId());

    }

    public FilmModel read(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Film not found with name: " + id));

        return FilmMapper.INSTANCE.toModel(film);
    }

    public void delete(Long cinemaId, Long filmId) {
        filmRepository.deleteById(filmId);
    }


    public List<FilmModel> getAll(Long cinemaId) {
        List<Long> filmsId = cinemaFilmRepository.findByCinemaId(cinemaId);
        return filmsId.stream()
                .map(id -> FilmMapper.INSTANCE.toModel(filmRepository.findById(id).orElse(null)))
                .collect(Collectors.toList());
    }


}
