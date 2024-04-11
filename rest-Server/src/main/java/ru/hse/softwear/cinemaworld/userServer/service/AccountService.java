package ru.hse.softwear.cinemaworld.userServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Session;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.TicketMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.UserMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.TicketModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.UserModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final SessionRepository sessionRepository;
    private final TicketRepository ticketRepository;
    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    public UserModel getUserInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        return UserMapper.INSTANCE.toModel(user);
    }

    public void updateUserInfo(Long id, UserModel userModel) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        user.setName(Optional.ofNullable(userModel.getName()).orElse(user.getName()));
        user.setPassword(Optional.ofNullable(userModel.getPassword()).orElse(user.getPassword()));
        user.setNumberPhone(Optional.ofNullable(userModel.getNumberPhone()).orElse(user.getNumberPhone()));

        userRepository.save(user.getEmail(),user.getPassword(), user.getName(), user.getNumberPhone());
    }

    public List<Map<String, Object>> getOrders(Long id) {
        List<Order> orders = orderRepository.findAllByUserId(id);
        List<Map<String, Object>> result = new ArrayList<>();

        for(Order order : orders) {
            Map<String, Object> orderInfo = new HashMap<>();

            Session session = sessionRepository.findById(order.getSessionId()).orElse(null);
            String cinemaName = cinemaRepository.findById(session.getCinemaId()).orElse(null).getName();
            String filmName = filmRepository.findById(session.getFilmId()).orElse(null).getName();
            String hallName = hallRepository.findById(session.getHallId()).orElse(null).getName();
            List<Ticket> tickets = ticketRepository.findAllByOrderId(order.getId());

            orderInfo.put("session", SessionMapper.INSTANCE.toModel(session));
            orderInfo.put("cinemaName", cinemaName);
            orderInfo.put("filmName", filmName);
            orderInfo.put("hallName", hallName);
            orderInfo.put("countTicket", tickets.size());

            result.add(orderInfo);
        }

        return result;
    }


}
