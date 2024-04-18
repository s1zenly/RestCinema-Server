package ru.hse.softwear.cinemaworld.userServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.userServer.cypher.OrderTokenCypher;
import ru.hse.softwear.cinemaworld.userServer.service.OrderService;
import ru.hse.softwear.cinemaworld.userServer.service.RedisService;
import ru.hse.softwear.cinemaworld.userServer.view.dto.OrderPageDTO;
import ru.hse.softwear.cinemaworld.userServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.userServer.view.model.SessionIdModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.CinemaModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.FilmModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.HallModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void createOrder(@PathVariable String orderToken) throws Exception{

        final JwtAuthentication jwtInfoToken = authService.getAuthInfo();
        Long sessionId = (Long) OrderTokenCypher.decoder(orderToken).get("sessionId");
        List<OccupiedPlace> occupiedPlaces = redisService.getInCacheOrdersSession(orderToken);

        redisService.deleteInCacheOrderSession(orderToken);
        orderService.saveCompletedOrder((Long) jwtInfoToken.getPrincipal(), sessionId, occupiedPlaces, orderToken);
    }

    @GetMapping("/session/{id}/{orderToken}")
    public ResponseEntity<OrderPageDTO> getOrderPage(@PathVariable Long id,
                                                     @PathVariable String orderToken) throws Exception {

        Map<String, Object> infoAboutSession = orderService.getDataSession(id);

        List<OccupiedPlace> occupiedPlaces = new ArrayList<>();
        List<OccupiedPlace> occupiedPlacesSecured = orderService.getOccupiedPlace(id);
        List<OccupiedPlace> occupiedPlacesTemporally =
                orderService.getTemporallyOccupiedPlace((Long) OrderTokenCypher.decoder(orderToken).get("sessionId"));

        occupiedPlaces.addAll(occupiedPlacesSecured);
        occupiedPlaces.addAll(occupiedPlacesTemporally);

        OrderPageDTO orderPageDTO = new OrderPageDTO();
        orderPageDTO.setSession((SessionModel) infoAboutSession.get("session"));
        orderPageDTO.setCinema((CinemaModel) infoAboutSession.get("cinema"));
        orderPageDTO.setFilm((FilmModel) infoAboutSession.get("film"));
        orderPageDTO.setHall((HallModel) infoAboutSession.get("hall"));
        orderPageDTO.setOccupiedPlaces(occupiedPlaces);

        return ResponseEntity.ok(orderPageDTO);
    }

    @GetMapping("/checkout/{orderToken}")
    ResponseEntity<OrderPageDTO> checkoutConfirmation(@PathVariable String orderToken) throws Exception {

        Long id = (Long) OrderTokenCypher.decoder(orderToken).get("sessionId");
        Map<String, Object> infoAboutSession = orderService.getDataSession(id);
        List<OccupiedPlace> occupiedPlaces = redisService.getInCacheOrdersSession(orderToken);

        OrderPageDTO orderPageDTO = new OrderPageDTO();
        orderPageDTO.setSession((SessionModel) infoAboutSession.get("session"));
        orderPageDTO.setCinema((CinemaModel) infoAboutSession.get("cinema"));
        orderPageDTO.setFilm((FilmModel) infoAboutSession.get("film"));
        orderPageDTO.setHall((HallModel) infoAboutSession.get("hall"));
        orderPageDTO.setOccupiedPlaces(occupiedPlaces);

        return ResponseEntity.ok(orderPageDTO);
    }
}
