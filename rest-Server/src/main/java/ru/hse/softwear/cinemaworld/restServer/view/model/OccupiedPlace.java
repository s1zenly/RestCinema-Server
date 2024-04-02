package ru.hse.softwear.cinemaworld.restServer.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@RedisHash("OccupiedPlace")
public class OccupiedPlace {
    private Integer row;
    private Integer column;
}
