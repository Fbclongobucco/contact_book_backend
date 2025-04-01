package com.buccodev.contact_book.infrastructure.services.user_service;

import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.core.domain.User;
import com.buccodev.contact_book.infrastructure.repositories.entities.UserEntity;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.UserEntityRepository;
import com.buccodev.contact_book.infrastructure.services.exceptions.CredentialsInvalidException;
import com.buccodev.contact_book.infrastructure.services.exceptions.InvalidQueryParametersException;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;
import com.buccodev.contact_book.infrastructure.services.utils.UserServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserService implements GetUser {

    private final UserEntityRepository repository;

    public GetUserService(UserEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    public User getUserById(Long id) {

        var userEntity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        return UserServiceMapper.fromUserEntityToUser(userEntity);
    }


    @Override
    public User getUserByEmail(String email) {

        var userEntity = repository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        return UserServiceMapper.fromUserEntityToUser(userEntity);
    }


    @Override
    public User login(String email, String password) {

        var userEntity = repository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(!userEntity.getPassword().equals(password)) {
            throw new CredentialsInvalidException("Invalid credendials");
        }
        return UserServiceMapper.fromUserEntityToUser(userEntity);
    }

    @Override
    public List<User> getAllUsers(Integer page, Integer size) {

        if(page < 0 || size < 0) {
            throw new InvalidQueryParametersException("Invalid query parameters");
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserEntity> userEntities = repository.findAll(pageRequest);

       return userEntities.getContent().stream().map(UserServiceMapper::fromUserEntityToUser).toList();
    }
}
