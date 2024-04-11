package ru.hse.softwear.cinemaworld.userServer.view.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Data
@Table(name = "orders")
public class Order {
    @Id
    private Long id;
    private String token;

    // reference
    private Long sessionId;
    private Long userId;
}
