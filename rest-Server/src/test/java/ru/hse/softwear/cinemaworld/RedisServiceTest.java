package ru.hse.softwear.cinemaworld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import ru.hse.softwear.cinemaworld.restServer.service.RedisService;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    /*@MockBean
    private RestTemplate restTemplateRefreshToken;*/

    @MockBean
    private RestTemplate restTemplateCacheOrderSession;

    @BeforeEach
    public void setUp() {
        Mockito.reset(restTemplateCacheOrderSession);
    }

    @Test
    public void testSetInRefreshToken() {
        String key = "String";
        String value = "String";


        redisService.setInRefreshToken(key, value);
    }

    @Test
    public void testSetInCacheOrderSession() {
        String key = "Object";
        List<OccupiedPlace> value = new ArrayList<>();


        redisService.setInCacheOrdersSession(key, value);
    }
}
