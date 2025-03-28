package com.buccodev.contact_book.infrastructure.services.user_service;

import com.buccodev.contact_book.application.usecases.user_usecases.DeleteUser;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.UserEntityRepository;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;

public class DeleteUserService implements DeleteUser {

    private final UserEntityRepository repository;

    public DeleteUserService(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteUserById(Long id) {

        var userEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        repository.deleteById(userEntity.getId());

    }
}
