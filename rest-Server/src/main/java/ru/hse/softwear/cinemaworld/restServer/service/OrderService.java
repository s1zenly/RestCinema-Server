package ru.hse.softwear.cinemaworld.restServer.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.entity.*;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.SessionMapper;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.restServer.view.repository.OrderRepository;
import ru.hse.softwear.cinemaworld.restServer.view.repository.SessionRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final SessionRepository sessionRepository;

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

    public String generateMD5Token() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        byte[] digest = messageDigest.digest(bytes);

        StringBuilder sb = new StringBuilder(digest.length);
        for(byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
