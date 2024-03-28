package ru.hse.softwear.cinemaworld.admin.Service.crudServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.admin.Service.CRUDservice;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.HallMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.HallModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.HallRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallService implements CRUDservice<HallModel, Long> {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public void create(Object... objects) {
        String cinemaName = (String) objects[0];
        HallModel hallDTO = (HallModel) objects[1];

        Cinema cinema = cinemaRepository.findByName(cinemaName)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + cinemaName));

        Hall hall = HallMapper.INSTANCE.toEntity(hallDTO);

        cinema.getHalls().add(hall);
        hall.setCinema(cinema);

        hallRepository.save(hall);
    }

    @Override
    public HallModel read(Long id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + id));

        return HallMapper.INSTANCE.toModel(hall);
    }

    @Override
    public void update(Long id, HallModel hallDTO) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + id));

        // Mutable data
        hall.setName(Optional.ofNullable(hallDTO.getName()).orElse(hall.getName()));
        hall.setRows(Optional.ofNullable(hallDTO.getRows()).orElse(hall.getRows()));
        hall.setColumns(Optional.ofNullable(hallDTO.getColumns()).orElse(hall.getColumns()));

        hallRepository.save(hall);
    }

    @Override
    public void delete(Object... objects) {
        String cinemaName = (String) objects[0];
        Long id = (Long) objects[1];

        Cinema cinema = cinemaRepository.findByName(cinemaName)
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with name: " + cinemaName));

        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + id));

        cinema.getHalls().remove(hall);
        hall.setCinema(null);

        hallRepository.deleteById(id);
    }

    public List<HallModel> getAll(String cinemaName) {
        return hallRepository.findAll().stream()
                .filter(hall -> hall.getCinema().getName().equalsIgnoreCase(cinemaName))
                .map(HallMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }
}
