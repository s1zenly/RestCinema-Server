package ru.hse.softwear.cinemaworld.restServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.hse.softwear.cinemaworld.authServer.service.AuthService;
import ru.hse.softwear.cinemaworld.authServer.view.JwtAuthentication;
import ru.hse.softwear.cinemaworld.restServer.service.AccountService;
import ru.hse.softwear.cinemaworld.restServer.view.dto.TicketPageDTO;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.SessionModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.TicketModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.UserModel;

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

        Map<SessionModel, List<TicketModel>> orders = accountService.getOrders((Long) jwtInfoToken.getPrincipal());

        for(var entry : orders.entrySet()) {
            ticketsPageDTO.add(new TicketPageDTO(entry.getKey(), entry.getValue().size()));
        }

        return ResponseEntity.ok(ticketsPageDTO);
    }
}
