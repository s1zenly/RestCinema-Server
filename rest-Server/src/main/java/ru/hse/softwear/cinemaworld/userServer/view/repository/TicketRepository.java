package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
