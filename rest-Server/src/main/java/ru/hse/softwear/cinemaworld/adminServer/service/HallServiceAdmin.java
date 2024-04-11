package ru.hse.softwear.cinemaworld.adminServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.view.model.HallUpdateModel;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Hall;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.HallMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.HallModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.HallRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallServiceAdmin {

    private final HallRepository hallRepository;

    public void create(Long cinemaId, HallModel hallModel) {
        Hall hall = HallMapper.INSTANCE.toEntity(hallModel);
        hall.setCinemaId(cinemaId);

        hallRepository.save(hall.getName(), hall.getRows(), hall.getColumns(), hall.getCinemaId());
    }

    public HallModel read(Long id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + id));

        return HallMapper.INSTANCE.toModel(hall);
    }

    public void update(Long id, HallUpdateModel changes) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + id));

        // Mutable data
        String name = Optional.ofNullable(changes.getName()).orElse(hall.getName());
        Integer rows = Optional.ofNullable(changes.getRows()).orElse(hall.getRows());
        Integer columns = Optional.ofNullable(changes.getColumns()).orElse(hall.getColumns());

        hallRepository.update(id, name, rows, columns);
    }

    public void delete(Long id) {
        hallRepository.deleteById(id);
    }

    public List<HallModel> getAll(Long cinemaId) {
        return hallRepository.findAll().stream()
                .filter(hall -> hall.getCinemaId().equals(cinemaId))
                .map(HallMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }
}
