package ru.hse.softwear.cinemaworld.admin.service.crudServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.softwear.cinemaworld.restServer.view.model.SessionModel;

@Service
@RequiredArgsConstructor
public class SessionService {

    public void create(SessionModel sessionDTO) {
        String cinemaName = sessionDTO.getCinema().getName();
    }
}
