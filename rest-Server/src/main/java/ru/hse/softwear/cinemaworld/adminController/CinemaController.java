package ru.hse.softwear.cinemaworld.adminController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.restServer.view.model.Cinema;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CurrentPosterRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.SoonPosterRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/cinema")
public class CinemaController {

    private final CurrentPosterRepository currentPosterRepository;
    private final SoonPosterRepository soonPosterRepository;
    private final CinemaRepository cinemaRepository;

    @PostMapping("/add")
    public void addCinema(@RequestBody Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    @PostMapping("/update")
    public void updateCinema(@RequestBody Cinema cinema) {

    }

    @PostMapping("/delete")
    public void deleteCinema(@RequestBody Cinema cinema) {

    }


}