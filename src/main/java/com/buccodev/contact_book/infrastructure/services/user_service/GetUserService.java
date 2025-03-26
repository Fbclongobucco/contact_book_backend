package com.buccodev.contact_book.infrastructure.services.user_service;

import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.core.domain.User;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.UserEntityRepository;

import java.util.List;

public class GetUserService implements GetUser {

    private final UserEntityRepository repository;

    public GetUserService(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserByNameAndEmail(String name, String email) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public List<User> getAllUsers(Integer page, Integer size) {
        return List.of();
    }
}
