package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.gateway.exception.UserCredentialsException;
import com.buccodev.contact_book.application.gateway.exception.UserNotFoundException;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserLoginDto;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserResponseDto;
import com.buccodev.contact_book.application.utils.mappers.UserGatewayMapper;
import com.buccodev.contact_book.core.domain.User;

import java.util.List;

public class GetUserGateway {

    private final GetUser getUser;

    public GetUserGateway(GetUser getUser) {
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


    public UserResponseDto login(UserLoginDto userLoginDto) {
        var userSalved =  getUser.login(userLoginDto.email(), userLoginDto.password());

        if(userSalved == null) {
            throw new UserCredentialsException("Invalid credendials");
        }
        return UserGatewayMapper.fromUserToResponseDto(userSalved);
    }

    public List<UserResponseDto> getAllUsers(Integer page, Integer size) {
        List<User> users = getUser.getAllUsers(page, size);

        return users.stream().map(UserGatewayMapper::fromUserToResponseDto).toList();
    }
}
