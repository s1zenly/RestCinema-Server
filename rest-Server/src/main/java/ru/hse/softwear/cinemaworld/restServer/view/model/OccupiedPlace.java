package ru.hse.softwear.cinemaworld.restServer.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OccupiedPlace implements Serializable {
    private Integer row;
    private Integer column;
}
