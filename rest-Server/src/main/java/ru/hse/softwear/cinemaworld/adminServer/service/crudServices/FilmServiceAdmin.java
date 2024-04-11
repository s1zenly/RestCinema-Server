package ru.hse.softwear.cinemaworld.adminServer.service.crudServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.service.CRUDservice;
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
public class FilmServiceAdmin implements CRUDservice<FilmModel, Long> {

    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;
    private final CinemaFilmRepository cinemaFilmRepository;

    @Override
    public void create(Object... objects) {
        Long cinemaId = (Long) objects[0];
        FilmModel filmModel = (FilmModel) objects[1];
        Film film = FilmMapper.INSTANCE.toEntity(filmModel);

        filmRepository.save(film.getName(), film.getYear(), film.getProducer(),
                film.getDuration(), film.getActors(), film.getTrailer(), film.getInfo(),
                film.getCurrent(), film.getImage(), Optional.ofNullable(film.getAgeCategory()).map(AgeCategories::getValue).orElse(null), film.getProductionCountry());

        //cinemaFilmRepository.save(cinemaId, filmModel.getId());

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
        film.setTrailer(Optional.ofNullable(filmDTO.getTrailer()).orElse(film.getTrailer()));

        filmRepository.save(film.getName(), film.getYear(), film.getProducer(),
                film.getDuration(), film.getActors(), film.getTrailer(), film.getInfo(),
                film.getCurrent(), film.getImage(), film.getAgeCategory().name(), film.getProductionCountry());
    }

    @Override
    public void delete(Object... objects) {
        Long cinemaId = (Long) objects[0];
        Long filmId = (Long) objects[1];

        filmRepository.deleteById(filmId);
    }


    public List<FilmModel> getAll(Long cinemaId) {
        List<Long> filmsId = cinemaFilmRepository.findByCinemaId(cinemaId);
        return filmsId.stream()
                .map(id -> FilmMapper.INSTANCE.toModel(filmRepository.findById(id).orElse(null)))
                .collect(Collectors.toList());
    }


}
