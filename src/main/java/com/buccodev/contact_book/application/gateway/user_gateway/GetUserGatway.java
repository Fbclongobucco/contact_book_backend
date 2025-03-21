package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.core.domain.User;

import java.util.List;

public class GetUserGatway {

    private final GetUser getUser;

    public GetUserGatway(GetUser getUser) {
        this.getUser = getUser;
    }

    public User getUserById(Long id) {
        return getUser.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return getUser.getUserByEmail(email);
    }

    public User getUserByName(String name) {
        return getUser.getUserByName(name);
    }

    public User login(String email, String password) {
        return getUser.login(email, password);
    }

    public List<User> getAllUsers(Integer page, Integer size) {
        return getUser.getAllUsers(page, size);
    }
}
