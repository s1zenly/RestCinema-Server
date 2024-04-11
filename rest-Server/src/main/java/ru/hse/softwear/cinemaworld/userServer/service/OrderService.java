package ru.hse.softwear.cinemaworld.userServer.service;

import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.cypher.OrderTokenCypher;
import ru.hse.softwear.cinemaworld.userServer.view.entity.*;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.CinemaMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.FilmMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.HallMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RedisService redisService;
    private final OrderRepository orderRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final CinemaRepository cinemaRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;


    public Map<String, Object> getDataSession(Long id) {
        Map<String, Object> infoAboutSession = new HashMap<>();

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));
        Cinema cinema = cinemaRepository.findById(session.getCinemaId())
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with id: " + session.getCinemaId()));
        Film film = filmRepository.findById(session.getFilmId())
                .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + session.getFilmId()));
        Hall hall = hallRepository.findById(session.getHallId())
                .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + session.getHallId()));

        infoAboutSession.put("session", SessionMapper.INSTANCE.toModel(session));
        infoAboutSession.put("cinema", CinemaMapper.INSTANCE.toModel(cinema));
        infoAboutSession.put("film", FilmMapper.INSTANCE.toModel(film));
        infoAboutSession.put("hall", HallMapper.INSTANCE.toModel(hall));

        return infoAboutSession;
    }

    public List<OccupiedPlace> getOccupiedPlace(Long id) {
        List<Ticket> tickets = ticketRepository.findAllBySessionId(id);

        return tickets.stream()
                .map(ticket -> new OccupiedPlace(ticket.getSection(), ticket.getSubsection()))
                .collect(Collectors.toList());
    }

    public void saveCompletedOrder(Long userId, Long sessionId, List<OccupiedPlace> occupiedPlaces, String orderToken) {
        Order order = new Order();
        order.setToken(orderToken);
        order.setUserId(userId);
        order.setSessionId(sessionId);
        orderRepository.save(order.getToken(), order.getSessionId(), order.getUserId());

        Long orderId = orderRepository.findOrderByToken(orderToken);

        occupiedPlaces.forEach(occupiedPlace -> {
                    ticketRepository.save(occupiedPlace.getRow(), occupiedPlace.getColumn(), sessionId, orderId);
                });
    }

    public List<OccupiedPlace> getTemporallyOccupiedPlace(Long sessionId) {
        List<OccupiedPlace> occupiedPlaces = new ArrayList<>();
        Set<String> tokensTemporally = redisService.allKeysInCacheOrderSession();

        tokensTemporally
                .forEach(token -> {
                    try {
                        Long decodeSessionId = (Long) OrderTokenCypher.decoder(token).get("sessionId");
                        if(decodeSessionId.equals(sessionId)) {
                            List<OccupiedPlace> list = redisService.getInCacheOrdersSession(token);
                            occupiedPlaces.addAll(list);
                        }
                    } catch (Exception ignored) {}
                });

        return occupiedPlaces;
    }

    public String getOrderToken(Long userId, Long sessionId) throws Exception {
        return OrderTokenCypher.encoder(userId, sessionId);
    }

}
