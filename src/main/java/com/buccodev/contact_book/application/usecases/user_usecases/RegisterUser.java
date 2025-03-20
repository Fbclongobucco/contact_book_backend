package com.buccodev.contact_book.application.usecases;

import com.buccodev.contact_book.core.domain.User;

public interface UserUseCases {

    void registerUser(User user);

    User login(String email, String password);

    
}
