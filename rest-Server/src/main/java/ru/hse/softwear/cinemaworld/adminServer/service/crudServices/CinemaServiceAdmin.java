package ru.hse.softwear.cinemaworld.adminServer.service.crudServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.service.CRUDservice;
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
public class CinemaServiceAdmin implements CRUDservice<CinemaModel, Long> {

    private final CinemaRepository cinemaRepository;
    private final FilmRepository filmRepository;
    private final CinemaFilmRepository cinemaFilmRepository;

    @Override
    public void create(Object... objects) {
        CinemaModel cinemaDTO = (CinemaModel) objects[0];

        Cinema cinema = CinemaMapper.INSTANCE.toEntity(cinemaDTO);
        cinemaRepository.save(cinema.getName(), cinema.getLatitude(), cinema.getLongitude(),
                cinema.getInfo(), cinema.getNumberPhone(), cinema.getImage());
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

        cinemaRepository.save(cinema.getName(), cinema.getLatitude(), cinema.getLongitude(),
                cinema.getInfo(), cinema.getNumberPhone(), cinema.getImage());
    }

    @Override
    public void delete(Object... objects) {
        Long id = (Long) objects[0];

        cinemaRepository.deleteById(id);
    }
}
