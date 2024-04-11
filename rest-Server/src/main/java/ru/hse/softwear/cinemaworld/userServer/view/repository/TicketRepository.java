package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Modifying
    @Query("INSERT INTO tickets (section, subsection, session_id, order_id)" +
           "values (:section, :subsection, :sessionId, :orderId)")
    void save(Integer section, Integer subsection, Long sessionId, Long orderId);

    @Query("SELECT * FROM tickets WHERE session_id = :sessionId")
    List<Ticket> findAllBySessionId(Long sessionId);

    @Query("SELECT * FROM tickets WHERE order_id = :orderId")
    List<Ticket> findAllByOrderId(Long orderId);

    @Modifying
    @Query("DELETE FROM tickets WHERE id = :id")
    void deleteById(Long id);
}
