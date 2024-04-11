package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Query("SELECT * FROM tickets WHERE session_id = :sessionId")
    List<Ticket> findAllBySessionId(Long sessionId);

    @Query("SELECT * FROM tickets WHERE order_id = :orderId")
    List<Ticket> findAllByOrderId(Long orderId);

    @Query("DELETE FROM tickets WHERE id = :id")
    void deleteById(Long id);
}
