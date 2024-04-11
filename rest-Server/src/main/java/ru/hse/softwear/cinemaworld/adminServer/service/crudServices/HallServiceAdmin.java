package ru.hse.softwear.cinemaworld.adminServer.service.crudServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.adminServer.service.CRUDservice;
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
public class HallServiceAdmin implements CRUDservice<HallModel, Long> {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public void create(Object... objects) {
        Long cinemaId = (Long) objects[0];
        HallModel hallModel = (HallModel) objects[1];

        Hall hall = HallMapper.INSTANCE.toEntity(hallModel);
        hall.setCinemaId(cinemaId);

        hallRepository.save(hall.getName(), hall.getRows(), hall.getColumns(), hall.getCinemaId());
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

        hallRepository.save(hall.getName(), hall.getRows(), hall.getColumns(), hall.getCinemaId());
    }

    @Override
    public void delete(Object... objects) {
        Long id = (Long) objects[0];

        hallRepository.deleteById(id);
    }

    public List<HallModel> getAll(Long cinemaId) {
        return hallRepository.findAll().stream()
                .filter(hall -> hall.getCinemaId().equals(cinemaId))
                .map(HallMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }
}
