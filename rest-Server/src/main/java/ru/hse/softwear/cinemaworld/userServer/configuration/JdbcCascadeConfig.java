/*
package ru.hse.softwear.cinemaworld.userServer.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.hse.softwear.cinemaworld.userServer.view.entity.*;
import ru.hse.softwear.cinemaworld.userServer.view.repository.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@EnableJdbcRepositories(basePackages = "ru.hse.softwear.cinemaworld.userServer.view.repository")
public class JdbcCascadeConfig extends AbstractJdbcConfiguration {

    private final CinemaRepository cinemaRepository;
    private final FilmRepository filmRepository;
    private final CinemaFilmRepository cinemaFilmRepository;
    private final HallRepository hallRepository;
    private final SessionRepository sessionRepository;
    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @EventListener
    public void handleBeforeDeleteCinemaEvent(BeforeDeleteEvent<Cinema> event) {
        Cinema cinema = event.getEntity();

        List<Long> filmsId = cinemaFilmRepository.findByCinemaId(cinema.getId());

        for(Long filmId : filmsId) {
            cinemaFilmRepository.delete(cinema.getId(), filmId);
        }

        List<Hall> halls = hallRepository.findAllByCinemaId(cinema.getId());
        List<Session> sessions = sessionRepository.findAllByCinemaId(cinema.getId());

        for(Session session : sessions) {
            List<Ticket> tickets = ticketRepository.findAllBySessionId(session.getId());
            List<Order> orders = orderRepository.findAllBySessionId(session.getId());

            for(Ticket ticket : tickets) {
                ticketRepository.deleteById(ticket.getId());
            }

            for(Order order : orders) {
                orderRepository.deleteById(order.getId());
            }

            sessionRepository.deleteById(session.getId());
        }

        for(Hall hall : halls) {
            hallRepository.deleteById(hall.getId());
        }
    }

    @EventListener
    public void handleBeforeDeleteFilmEvent(BeforeDeleteEvent<Film> event) {
        Film film = event.getEntity();

        List<Long> cinemasId = cinemaFilmRepository.findByFilmId(film.getId());

        for(Long cinemaId : cinemasId) {
            cinemaFilmRepository.delete(cinemaId, film.getId());
        }

        List<Session> sessions = sessionRepository.findAllByFilmId(film.getId());


        for(Session session : sessions) {
            List<Ticket> tickets = ticketRepository.findAllBySessionId(session.getId());
            List<Order> orders = orderRepository.findAllBySessionId(session.getId());

            for(Ticket ticket : tickets) {
                ticketRepository.deleteById(ticket.getId());
            }

            for(Order order : orders) {
                orderRepository.deleteById(order.getId());
            }

            sessionRepository.deleteById(session.getId());
        }
    }

    @EventListener
    public void handlerBeforeDeleteHallEvent(BeforeDeleteEvent<Hall> event) {
        Hall hall = event.getEntity();

        List<Session> sessions = sessionRepository.findAllByHallId(hall.getId());


        for(Session session : sessions) {
            List<Ticket> tickets = ticketRepository.findAllBySessionId(session.getId());
            List<Order> orders = orderRepository.findAllBySessionId(session.getId());

            for(Ticket ticket : tickets) {
                ticketRepository.deleteById(ticket.getId());
            }

            for(Order order : orders) {
                orderRepository.deleteById(order.getId());
            }

            sessionRepository.deleteById(session.getId());
        }
    }

    @EventListener
    public void handlerBeforeDeleteSessionEvent(BeforeDeleteEvent<Session> event) {
        Session session = event.getEntity();

        List<Ticket> tickets = ticketRepository.findAllBySessionId(session.getId());
        List<Order> orders = orderRepository.findAllBySessionId(session.getId());

        for(Ticket ticket : tickets) {
            ticketRepository.deleteById(ticket.getId());
        }

        for(Order order : orders) {
            orderRepository.deleteById(order.getId());
        }

    }

    @EventListener
    public void handlerBeforeDeleteTicketEvent(BeforeDeleteEvent<Ticket> event) {
        Ticket ticket = event.getEntity();

        orderRepository.deleteById(ticket.getOrderId());
    }

    @EventListener
    public void handlerBeforeDeleteOrderEvent(BeforeDeleteEvent<Order> event) {
        Order order = event.getEntity();

        List<Ticket> tickets = ticketRepository.findAllByOrderId(order.getId());

        for(Ticket ticket : tickets) {
            ticketRepository.deleteById(ticket.getId());
        }
    }

    @EventListener
    public void handlerBeforeDeleteUserEvent(BeforeDeleteEvent<User> event) {
        User user = event.getEntity();

        List<Order> orders = orderRepository.findAllByUserId(user.getId());

        for(Order order : orders) {
            List<Ticket> tickets = ticketRepository.findAllByOrderId(order.getId());

            for(Ticket ticket : tickets) {
                ticketRepository.deleteById(ticket.getId());
            }

            orderRepository.deleteById(order.getId());
        }
    }
}
*/
