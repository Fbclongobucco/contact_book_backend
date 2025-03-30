package com.buccodev.contact_book.infrastructure.config.beans.beans_user;

import com.buccodev.contact_book.application.gateway.user_gateway.DeleteUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.GetUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.RegisterUserGateway;
import com.buccodev.contact_book.application.gateway.user_gateway.UpdateUserGateway;
import com.buccodev.contact_book.application.usecases.user_usecases.DeleteUser;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.usecases.user_usecases.RegisterUser;
import com.buccodev.contact_book.application.usecases.user_usecases.UpdateUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeans {

    @Bean
    RegisterUserGateway registerUserGateway(RegisterUser registerUser, GetUser getUser) {
        return new RegisterUserGateway(registerUser, getUser);
    }

    @Bean
    UpdateUserGateway updateUserGateway(UpdateUser updateUser, GetUser getUser) {
        return new UpdateUserGateway(updateUser, getUser);
    }

    @Bean
    DeleteUserGateway deleteUserGateway(DeleteUser deleteUser, GetUser getUser) {
        return new DeleteUserGateway(deleteUser, getUser);
    }

    @Bean
    GetUserGateway getUserGateway(GetUser getUser) {
        return new GetUserGateway(getUser);
    }
}
