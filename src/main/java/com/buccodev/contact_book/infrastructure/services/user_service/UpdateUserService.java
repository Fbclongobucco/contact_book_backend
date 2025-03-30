package com.buccodev.contact_book.infrastructure.services.user_service;

import com.buccodev.contact_book.application.usecases.user_usecases.UpdateUser;
import com.buccodev.contact_book.core.domain.User;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.UserEntityRepository;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;
import com.buccodev.contact_book.infrastructure.services.utils.UserServiceMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService implements UpdateUser {

    private final UserEntityRepository repository;

    public UpdateUserService(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void updateUser(Long id, User user) {

        var userEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        repository.save(userEntity);

    }

    @Transactional
    @Override
    public void updatePassword(Long id, String password) {

        var userEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        var user = UserServiceMapper.fromUserEntityToUser(userEntity);

        userEntity.setPassword(user.getPassword());

        repository.save(userEntity);
    }
}
