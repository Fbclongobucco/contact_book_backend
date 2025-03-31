package com.buccodev.contact_book.infrastructure.controllers.user_controller;

import com.buccodev.contact_book.application.gateway.contact_gateway.UpdateContactGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.DeleteUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.GetUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.RegisterUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.UpdateUserGateway;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("contact-book/api/user")
public class UserController {

    private final RegisterUserGateway  getUserGateway;
    private final UpdateUserGateway updateGateway;
    private final DeleteUserGateway deleteUserGateway;
    private final GetUserGateway getUserGatway;


    public UserController(RegisterUserGateway getUserGateway,
                          UpdateUserGateway updateGateway,
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

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) {

        updateGateway.updateUser(id, userUpdateDto);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,
                                               @Valid
                                               @RequestBody
                                               UpdatePasswordDto password) {

        updateGateway.updatePassword(id, password);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        deleteUserGateway.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {

        var responseDto = getUserGatway.getUserById(id);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {

        var responseDto = getUserGatway.getUserByEmail(email);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginDto userLoginDto) {

        var responseDto = getUserGatway.login(userLoginDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        var responseDto = getUserGatway.getAllUsers(page, size);

        return ResponseEntity.ok().body(responseDto);
    }


}
