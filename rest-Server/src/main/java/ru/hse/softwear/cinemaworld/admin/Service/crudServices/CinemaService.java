package ru.hse.softwear.cinemaworld.admin.Service.crudServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.admin.Service.CRUDservice;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.CinemaMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CinemaService implements CRUDservice<CinemaModel, String> {

    private final CinemaRepository cinemaRepository;

    @Override
    public void create(Object... objects) {
        CinemaModel cinemaDTO = (CinemaModel) objects[0];

        Cinema cinema = CinemaMapper.INSTANCE.toEntity(cinemaDTO);
        cinemaRepository.save(cinema);
    }

    @Override
    public CinemaModel read(String name) {
        Cinema cinema =  cinemaRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + name));

        return CinemaMapper.INSTANCE.toModel(cinema);
    }

    @Override
    public void update(String name, CinemaModel cinemaDTO) {
        Cinema cinema = cinemaRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + name));

        // Mutable data
        cinema.setInfo(Optional.ofNullable(cinemaDTO.getInfo()).orElse(cinema.getInfo()));
        cinema.setCoordinates(Optional.ofNullable(cinemaDTO.getCoordinates()).orElse(cinema.getCoordinates()));
        cinema.setNumberPhone(Optional.ofNullable(cinemaDTO.getNumberPhone()).orElse(cinema.getNumberPhone()));
        cinema.setImage(Optional.ofNullable(cinemaDTO.getImage()).orElse(cinema.getImage()));

        cinemaRepository.saveAndFlush(cinema);
    }

    @Override
    public void delete(Object... objects) {
        String cinemaName = (String) objects[0];

        Cinema cinema = cinemaRepository.findByName(cinemaName)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + cinemaName));

        cinemaRepository.deleteById(cinemaName);
    }
}
