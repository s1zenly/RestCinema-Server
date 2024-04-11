package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Modifying
    @Query("INSERT INTO orders (token, session_id, user_id)" +
           "values (:token, :sessionId, :userId)")
    void save(String token, Long sessionId, Long userId);

    @Query("SELECT id FROM orders WHERE token = :token")
    Long findOrderByToken(String token);

    @Query("SELECT * FROM orders WHERE session_id = :sessionId")
    List<Order> findAllBySessionId(Long sessionId);

    @Query("SELECT * FROM orders WHERE user_id = :userId")
    List<Order> findAllByUserId(Long userId);

    @Query("SELECT * FROM orders WHERE session_id = :sessionId AND user_id = :userId")
    Optional<Order> findBySessionIdAndUserId(Long sessionId, Long userId);

    @Modifying
    @Query("DELETE FROM orders WHERE id = :id")
    void deleteById(Long id);
}
