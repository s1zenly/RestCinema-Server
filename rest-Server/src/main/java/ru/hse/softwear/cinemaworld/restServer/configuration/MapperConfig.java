package ru.hse.softwear.cinemaworld.restServer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithDependency.*;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.CinemaMapperWithoutDependency;
import ru.hse.softwear.cinemaworld.restServer.view.mapper.mapperWithoutDependency.FilmMapperWithoutDependency;

@Configuration
public class MapperConfig {

    @Bean
    public CinemaMapper cinemaMapper() {
        return CinemaMapper.INSTANCE;
    }
    @Bean
    public FilmMapper filmMapper() { return FilmMapper.INSTANCE; }
    @Bean
    public HallMapper hallMapper() { return HallMapper.INSTANCE; }
    @Bean
    public OrderMapper orderMapper() { return OrderMapper.INSTANCE; }
    @Bean
    public SessionMapper sessionMapper() { return SessionMapper.INSTANCE; }
    @Bean
    public TicketMapper ticketMapper() { return TicketMapper.INSTANCE; }
    @Bean
    public UserMapper userMapper() { return UserMapper.INSTANCE; }
}


