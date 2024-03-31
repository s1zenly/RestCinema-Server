package ru.hse.softwear.cinemaworld.restServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.restServer.service.OrderService;
import ru.hse.softwear.cinemaworld.restServer.view.dto.ConfirmationPageDTO;
import ru.hse.softwear.cinemaworld.restServer.view.dto.OrderPageDTO;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> getMD5Token() throws NoSuchAlgorithmException {
        return ResponseEntity.ok(orderService.generateMD5Token());
    }

    @GetMapping("/session/{id}/{orderToken}")
    public ResponseEntity<OrderPageDTO> getOrderPage(@PathVariable Long id,
                                                     @PathVariable String orderToken) {

        OrderPageDTO orderPageDTO = new OrderPageDTO();

        SessionModel infoAboutSession = orderService.getDataSession(id);
        List<OccupiedPlace> occupiedPlaces = orderService.getOccupiedPlace(id);

        orderPageDTO.setSessionModel(infoAboutSession);
        orderPageDTO.setOccupiedPlaces(occupiedPlaces);

        return ResponseEntity.ok(orderPageDTO);
    }

    @GetMapping ResponseEntity<ConfirmationPageDTO> checkoutConfirmation() {
        return null;
    }
}
