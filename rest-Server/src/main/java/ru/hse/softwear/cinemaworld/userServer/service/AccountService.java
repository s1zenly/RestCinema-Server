package ru.hse.softwear.cinemaworld.userServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Order;
import ru.hse.softwear.cinemaworld.userServer.view.entity.User;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency.SessionMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency.TicketMapper;
import ru.hse.softwear.cinemaworld.userServer.view.mapper.mapperWithDependency.UserMapper;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.TicketModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.UserModel;
import ru.hse.softwear.cinemaworld.userServer.view.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

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

        userRepository.save(user);
    }

    public Map<SessionModel, List<TicketModel>> getOrders(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        List<Order> orders = user.getOrders();


        return orders.stream()
                .collect(Collectors.toMap(
                        order -> SessionMapper.INSTANCE.toModel(order.getSession()),
                        order -> order.getTickets().stream()
                                .map(TicketMapper.INSTANCE::toModel)
                                .collect(Collectors.toList())));
    }


}
