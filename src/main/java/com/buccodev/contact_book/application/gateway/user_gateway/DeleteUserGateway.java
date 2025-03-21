package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.usecases.user_usecases.DeleteUser;

public class DeleteUserGateway {

    private final DeleteUser deleteUser;

    public DeleteUserGateway(DeleteUser deleteUser) {
        this.deleteUser = deleteUser;
    }

    public void deleteUserById(Long id) {
        deleteUser.deleteUserById(id);
    }

    public void deleteAllUsersByUser(Long userId) {
        deleteUser.deleteAllUsersByUser(userId);
    }
}
