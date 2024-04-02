package ru.hse.softwear.cinemaworld.restServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.restServer.cypher.OrderTokenCypher;
import ru.hse.softwear.cinemaworld.restServer.service.OrderService;
import ru.hse.softwear.cinemaworld.restServer.service.RedisService;
import ru.hse.softwear.cinemaworld.restServer.view.dto.OrderPageDTO;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionIdModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final RedisService redisService;
    private final AuthService authService;


    // Redirect
    @PostMapping("/create")
    public ResponseEntity<String> getOrderToken(@RequestBody SessionIdModel sessionId) throws Exception {
        final JwtAuthentication jwtInfoToken = authService.getAuthInfo();

        return ResponseEntity.ok(orderService.getOrderToken((Long) jwtInfoToken.getPrincipal(), sessionId.getSessionId()));
    }

    // Redirect
    @PostMapping("/checkout/{orderToken}")
    public void bookSeats(@PathVariable String orderToken,
                          @RequestBody List<OccupiedPlace> occupiedPlaces) {
        redisService.setInCacheOrdersSession(orderToken, occupiedPlaces);
    }

    // Redirect
    @PostMapping("/completed/{orderToken}")
    public void createOrder(@PathVariable String orderToken,
                            @RequestBody List<OccupiedPlace> occupiedPlaces) throws Exception{

        final JwtAuthentication jwtInfoToken = authService.getAuthInfo();
        Long sessionId = (Long) OrderTokenCypher.decoder(orderToken).get("sessionId");

        redisService.deleteInCacheOrderSession(orderToken);
        orderService.saveCompletedOrder((Long) jwtInfoToken.getPrincipal(), sessionId, occupiedPlaces, orderToken);
    }

    @GetMapping("/session/{id}/{orderToken}")
    public ResponseEntity<OrderPageDTO> getOrderPage(@PathVariable Long id,
                                                     @PathVariable String orderToken) throws Exception {

        OrderPageDTO orderPageDTO = new OrderPageDTO();

        SessionModel infoAboutSession = orderService.getDataSession(id);
        List<OccupiedPlace> occupiedPlaces = new ArrayList<>();
        List<OccupiedPlace> occupiedPlacesSecured = orderService.getOccupiedPlace(id);
        List<OccupiedPlace> occupiedPlacesTemporally =
                orderService.getTemporallyOccupiedPlace((Long) OrderTokenCypher.decoder(orderToken).get("sessionId"));

        occupiedPlaces.addAll(occupiedPlacesSecured);
        occupiedPlaces.addAll(occupiedPlacesTemporally);

        orderPageDTO.setSessionModel(infoAboutSession);
        orderPageDTO.setOccupiedPlaces(occupiedPlaces);

        return ResponseEntity.ok(orderPageDTO);
    }

    @GetMapping("/checkout/{orderToken}")
    ResponseEntity<OrderPageDTO> checkoutConfirmation(@PathVariable String orderToken) throws Exception {
        OrderPageDTO orderPageDTO = new OrderPageDTO();

        Long id = (Long) OrderTokenCypher.decoder(orderToken).get("sessionId");
        SessionModel infoAboutSession = orderService.getDataSession(id);
        List<OccupiedPlace> occupiedPlaces = redisService.getInCacheOrdersSession(orderToken);

        orderPageDTO.setSessionModel(infoAboutSession);
        orderPageDTO.setOccupiedPlaces(occupiedPlaces);

        return ResponseEntity.ok(orderPageDTO);
    }
}
