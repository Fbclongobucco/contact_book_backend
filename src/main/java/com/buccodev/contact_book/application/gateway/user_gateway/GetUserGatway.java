package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.gateway.exception.UserCredendialsException;
import com.buccodev.contact_book.application.gateway.exception.UserNotFoundException;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserResponseDto;
import com.buccodev.contact_book.application.utils.mappers.UserGatewayMapper;
import com.buccodev.contact_book.core.domain.User;

import java.util.List;

public class GetUserGatway {

    private final GetUser getUser;

    public GetUserGatway(GetUser getUser) {
        this.getUser = getUser;
    }

    public UserResponseDto getUserById(Long id) {

        var userSalved = getUser.getUserById(id);

        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }

       return  UserGatewayMapper.fromUserToResponseDto(userSalved);
    }

    public UserResponseDto getUserByEmail(String email) {

        var userSalved = getUser.getUserByEmail(email);
        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }
        return UserGatewayMapper.fromUserToResponseDto(userSalved);
    }

    public UserResponseDto getUserByName(String name, String email) {
        var userSalved = getUser.getUserByNameAndEmail(name, email);

        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }
        return UserGatewayMapper.fromUserToResponseDto(userSalved);
    }

    public UserResponseDto login(String email, String password) {
        var userSalved =  getUser.login(email, password);

        if(userSalved == null) {
            throw new UserCredendialsException("Invalid credendials");
        }
        return UserGatewayMapper.fromUserToResponseDto(userSalved);
    }

    public List<UserResponseDto> getAllUsers(Integer page, Integer size) {
        List<User> users = getUser.getAllUsers(page, size);

        return users.stream().map(UserGatewayMapper::fromUserToResponseDto).toList();
    }
}
