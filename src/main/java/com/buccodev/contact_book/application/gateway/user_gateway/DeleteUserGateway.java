package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.gateway.exception.UserNotFoundException;
import com.buccodev.contact_book.application.usecases.user_usecases.DeleteUser;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;

public class DeleteUserGateway {

    private final DeleteUser deleteUser;
    private final GetUser getUser;

    public DeleteUserGateway(DeleteUser deleteUser, GetUser getUser) {
        this.deleteUser = deleteUser;
        this.getUser = getUser;
    }

    public void deleteUserById(Long id) {

        var userSalved = getUser.getUserById(id);

        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }
        deleteUser.deleteUserById(id);
    }

}
