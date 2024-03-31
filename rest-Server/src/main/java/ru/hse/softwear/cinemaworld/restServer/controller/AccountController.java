package ru.hse.softwear.cinemaworld.restServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.softwear.cinemaworld.restServer.service.AccountService;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.TicketModel;
import ru.hse.softwear.cinemaworld.restServer.view.model.dbmodel.UserModel;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<UserModel> getInfoAboutUser() {
        return null;
    }


}
