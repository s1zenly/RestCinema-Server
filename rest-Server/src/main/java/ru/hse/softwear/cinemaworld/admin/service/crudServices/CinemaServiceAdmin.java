package ru.hse.softwear.cinemaworld.admin.service.crudServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.admin.service.CRUDservice;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.CinemaMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CinemaServiceAdmin implements CRUDservice<CinemaModel, Long> {

    private final CinemaRepository cinemaRepository;

    @Override
    public void create(Object... objects) {
        CinemaModel cinemaDTO = (CinemaModel) objects[0];

        Cinema cinema = CinemaMapper.INSTANCE.toEntity(cinemaDTO);
        cinemaRepository.save(cinema);
    }

    @Override
    public CinemaModel read(Long id) {
        Cinema cinema =  cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + id));

        return CinemaMapper.INSTANCE.toModel(cinema);
    }

    @Override
    public void update(Long id, CinemaModel cinemaDTO) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + id));

        // Mutable data
        cinema.setInfo(Optional.ofNullable(cinemaDTO.getInfo()).orElse(cinema.getInfo()));
        cinema.setNumberPhone(Optional.ofNullable(cinemaDTO.getNumberPhone()).orElse(cinema.getNumberPhone()));
        cinema.setImage(Optional.ofNullable(cinemaDTO.getImage()).orElse(cinema.getImage()));

        cinemaRepository.saveAndFlush(cinema);
    }

    @Override
    public void delete(Object... objects) {
        Long id = (Long) objects[0];

        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + id));

        cinemaRepository.deleteById(id);
    }
}
