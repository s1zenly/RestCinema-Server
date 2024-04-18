package ru.hse.softwear.cinemaworld.adminServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.view.model.CinemaUpdateModel;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.CinemaMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaFilmRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.FilmRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CinemaServiceAdmin {

    private final CinemaRepository cinemaRepository;

    public void create(CinemaModel cinemaModel) {

        Cinema cinema = CinemaMapper.INSTANCE.toEntity(cinemaModel);
        cinemaRepository.save(cinema.getName(), cinema.getLatitude(), cinema.getLongitude(),
                cinema.getInfo(), cinema.getNumberPhone(), cinema.getImage());
    }

    public CinemaModel read(Long id) {
        Cinema cinema =  cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with id: " + id));

        return CinemaMapper.INSTANCE.toModel(cinema);
    }

    public void update(Long id, CinemaUpdateModel changes) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + id));

        // Mutable data
        String info = Optional.ofNullable(changes.getInfo()).orElse(cinema.getInfo());
        Long numberPhone = Optional.ofNullable(changes.getNumberPhone()).orElse(cinema.getNumberPhone());
        String image = Optional.ofNullable(changes.getImage()).orElse(cinema.getImage());

        cinemaRepository.update(id, info, numberPhone, image);
    }


    public void delete(Long id) {
        cinemaRepository.deleteById(id);
    }
}
