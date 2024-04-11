package ru.hse.softwear.cinemaworld.userServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.userServer.service.AccountService;
import ru.hse.softwear.cinemaworld.userServer.view.dto.TicketPageDTO;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.TicketModel;
import ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping({"", "/settings"})
    public ResponseEntity<UserModel> getInfoAboutUser() {
        final JwtAuthentication jwtInfoToken = authService.getAuthInfo();

        UserModel userModel = accountService.getUserInfo((Long) jwtInfoToken.getPrincipal());
        return ResponseEntity.ok(userModel);
    }

    @PutMapping("/settings")
    public void updateUserInfo(@RequestBody UserModel userModel) {
        final JwtAuthentication jwtInfoToken = authService.getAuthInfo();

        accountService.updateUserInfo((Long) jwtInfoToken.getPrincipal(), userModel);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketPageDTO>> getOrdersUser() {
        final JwtAuthentication jwtInfoToken = authService.getAuthInfo();

        List<TicketPageDTO> ticketsPageDTO = new ArrayList<>();
        List<Map<String, Object>> ordersInfo = accountService.getOrders((Long) jwtInfoToken.getPrincipal());

        for(Map<String, Object> orderInfo : ordersInfo) {
            TicketPageDTO ticketPageDTO = new TicketPageDTO();
            ticketPageDTO.setSession((SessionModel) orderInfo.get("session"));
            ticketPageDTO.setCinemaName(orderInfo.get("cinemaName").toString());
            ticketPageDTO.setFilmName(orderInfo.get("filmName").toString());
            ticketPageDTO.setHallName(orderInfo.get("hallName").toString());
            ticketPageDTO.setCountTickets((Integer) orderInfo.get("countTicket"));

            ticketsPageDTO.add(ticketPageDTO);
        }

        return ResponseEntity.ok(ticketsPageDTO);
    }
}
