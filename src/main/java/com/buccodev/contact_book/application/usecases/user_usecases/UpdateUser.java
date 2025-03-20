package com.buccodev.contact_book.application.usecases.user_usecases;

import com.buccodev.contact_book.core.domain.User;

public interface UpdateUser {

    void updateUser(Long id, User user);

    void updatePassword(Long id, String password);
}
