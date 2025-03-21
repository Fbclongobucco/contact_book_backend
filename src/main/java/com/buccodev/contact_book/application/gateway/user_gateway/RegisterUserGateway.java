package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.usecases.user_usecases.RegisterUser;
import com.buccodev.contact_book.core.domain.User;

public class RegisterUserGateway {

    private final RegisterUser registerUser;

    public RegisterUserGateway(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    public User registerUser(User user) {
        return registerUser.registerUser(user);
    }

}
