package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.usecases.user_usecases.UpdateUser;
import com.buccodev.contact_book.core.domain.User;

public class UpdateUserGateway {

    private final UpdateUser updateUser;

    public UpdateUserGateway(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    public void updateUser(Long id, User user) {
        updateUser.updateUser(id, user);
    }

    public void updatePassword(Long id, String password) {
        updateUser.updatePassword(id, password);
    }
}
