package com.buccodev.contact_book.infrastructure.services.user_service;

import com.buccodev.contact_book.application.usecases.user_usecases.RegisterUser;
import com.buccodev.contact_book.core.domain.User;
import com.buccodev.contact_book.infrastructure.repositories.entities.UserEntity;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.UserEntityRepository;
import com.buccodev.contact_book.infrastructure.services.utils.UserServiceMapper;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUser {

    private final UserEntityRepository repository;

    public RegisterUserService(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public User registerUser(User user) {

        var userEntity = new UserEntity(user);
        var userSaved = repository.save(userEntity);

        return UserServiceMapper.fromUserEntityToUser(userSaved);
    }
}
