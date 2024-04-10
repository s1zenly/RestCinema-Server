package ru.hse.softwear.cinemaworld.userServer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.cypher.OrderTokenCypher;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.OrderRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.SessionRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.TicketRepository;
import ru.hse.softwear.cinemaworld.userServer.view.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RedisService redisService;
    private final OrderRepository orderRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;


    public SessionModel getDataSession(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));

        return SessionMapper.INSTANCE.toModel(session);
    }

    public List<OccupiedPlace> getOccupiedPlace(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Session not found with id: " + id));

        List<Ticket> tickets = session.getTickets();

        return tickets.stream()
                .map(ticket -> new OccupiedPlace(ticket.getSection(), ticket.getSubsection()))
                .collect(Collectors.toList());
    }

    public void saveCompletedOrder(Long userId, Long sessionId, List<OccupiedPlace> occupiedPlaces, String orderToken) {
        Order order = new Order();
        order.setTickets(new ArrayList<>());

        List<Ticket> tickets = occupiedPlaces.stream()
                .map(occupiedPlace -> {
                    Ticket ticket = new Ticket();
                    ticket.setSection(occupiedPlace.getRow());
                    ticket.setSubsection(occupiedPlace.getColumn());
                    return ticket;
                })
                .toList();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("Session not fount with id: " + sessionId));

        ticketRepository.saveAll(tickets);

        session.getTickets().addAll(tickets);
        sessionRepository.save(session);

        order.setSession(session);
        order.setUser(user);
        order.getTickets().addAll(tickets);
        order.setToken(orderToken);
        orderRepository.save(order);

        tickets.forEach(ticket -> {
            ticket.setSession(session);
            ticket.setOrder(order);
            ticketRepository.save(ticket);
        });

        user.getOrders().add(order);
        userRepository.save(user);
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
