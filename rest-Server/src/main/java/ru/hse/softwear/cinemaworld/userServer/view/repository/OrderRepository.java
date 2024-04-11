package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("SELECT * FROM orders WHERE session_id = :sessionId")
    List<Order> findAllBySessionId(Long sessionId);

    @Query("SELECT * FROM orders WHERE user_id = :userId")
    List<Order> findAllByUserId(Long userId);
    @Query("DELETE FROM orders WHERE id = :id")
    void deleteById(Long id);
}
