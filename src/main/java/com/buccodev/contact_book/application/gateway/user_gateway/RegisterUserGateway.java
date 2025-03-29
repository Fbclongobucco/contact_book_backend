package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.gateway.exception.UserDuplicateException;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.usecases.user_usecases.RegisterUser;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserRequestDto;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserResponseDto;
import com.buccodev.contact_book.application.utils.mappers.UserGatewayMapper;
import com.buccodev.contact_book.core.domain.User;

public class RegisterUserGateway {

    private final RegisterUser registerUser;
    private final GetUser getUser;

    public RegisterUserGateway(RegisterUser registerUser, GetUser getUser) {
        this.registerUser = registerUser;
        this.getUser = getUser;
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        User user = UserGatewayMapper.fromUserRequestDtoToUser(userRequestDto);

        User registeredUser = getUser.getUserByNameAndEmail(user.getName(), user.getEmail());

        if (registeredUser != null) {
           throw new UserDuplicateException("User already exists");
        }

        User userSaved = registerUser.registerUser(user);

        return UserGatewayMapper.fromUserToResponseDto(userSaved);
    }

}
