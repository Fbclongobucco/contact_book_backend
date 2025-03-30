package com.buccodev.contact_book.infrastructure.controllers.user_controller;

import com.buccodev.contact_book.application.gateway.contact_gateway.UpdateContactGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.DeleteUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.GetUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.RegisterUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.UpdateUserGateway;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserRequestDto;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("contact-book/api/user")
public class UserController {

    private final RegisterUserGateway  getUserGateway;
    private final UpdateUserGateway updateGateway;
    private final DeleteUserGateway deleteUserGateway;
    private final GetUserGateway getUserGatway;


    public UserController(RegisterUserGateway getUserGateway,
                          UpdateContactGateway updateContactGateway, UpdateUserGateway updateGateway,
                          DeleteUserGateway deleteUserGateway,
                          GetUserGateway getUserGatway) {
        this.getUserGateway = getUserGateway;
        this.updateGateway = updateGateway;
        this.deleteUserGateway = deleteUserGateway;
        this.getUserGatway = getUserGatway;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        var responseDto = getUserGateway.registerUser(userRequestDto);

        return ResponseEntity.created(URI.create("/api/user/"+ responseDto.id())).body(responseDto);
    }

}
