package ru.hse.softwear.cinemaworld.restServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.CinemaMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public List<CinemaModel> getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();

        return cinemas.stream()
                .map(CinemaMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }

    public CinemaModel getCinema(Long id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with id: " + id));

        return CinemaMapper.INSTANCE.toModel(cinema);
    }
}
