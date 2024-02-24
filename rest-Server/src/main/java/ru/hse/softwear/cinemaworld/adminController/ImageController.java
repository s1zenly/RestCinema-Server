package ru.hse.softwear.cinemaworld.adminController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.restServer.view.model.interfaces.Image;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CinemaRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.CurrentPosterRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.SoonPosterRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/image")
public class ImageController {

    private final CurrentPosterRepository currentPosterRepository;
    private final SoonPosterRepository soonPosterRepository;
    private final CinemaRepository cinemaRepository;

    @PostMapping("/add")
    public void addImage(@RequestBody Image image) {

    }

    @PostMapping("/update")
    public void updateImage(@RequestBody Image image) {

    }

    @PostMapping("/delete")
    public void deleteImage(@RequestBody Image image) {

    }


}
