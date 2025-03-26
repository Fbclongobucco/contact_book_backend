package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.gateway.exception.UserDuplicateException;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.usecases.user_usecases.RegisterUser;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserRequestDto;
import com.buccodev.contact_book.application.utils.mappers.UserMapper;
import com.buccodev.contact_book.core.domain.User;

public class RegisterUserGateway {

    private final RegisterUser registerUser;
    private final GetUser getUser;

    public RegisterUserGateway(RegisterUser registerUser, GetUser getUser) {
        this.registerUser = registerUser;
        this.getUser = getUser;
    }

    public User registerUser(UserRequestDto userRequestDto) {

        User user = UserMapper.fromRequestDtoToUser(userRequestDto);

        User registeredUser = getUser.getUserByNameAndEmail(user.getName(), user.getEmail());

        if (registeredUser != null) {
           throw new UserDuplicateException("User already exists");
        }

        return registerUser.registerUser(user);
    }

}
