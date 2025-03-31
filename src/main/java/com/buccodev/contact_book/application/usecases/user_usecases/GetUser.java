package com.buccodev.contact_book.application.usecases.user_usecases;

import com.buccodev.contact_book.core.domain.User;

import java.util.List;

public interface GetUser {

    User getUserById(Long id);

    User getUserByEmail(String email);

    User login(String email, String password);

    List<User> getAllUsers(Integer page, Integer size);

}
