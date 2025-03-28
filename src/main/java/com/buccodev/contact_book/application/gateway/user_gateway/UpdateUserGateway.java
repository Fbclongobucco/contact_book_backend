package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.gateway.exception.UserNotFoundException;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.usecases.user_usecases.UpdateUser;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserRequestDto;
import com.buccodev.contact_book.application.utils.mappers.UserGatewayMapper;
import com.buccodev.contact_book.core.domain.User;

public class UpdateUserGateway {

    private final UpdateUser updateUser;
    private final GetUser getUser;

    public UpdateUserGateway(UpdateUser updateUser, GetUser getUser) {
        this.updateUser = updateUser;
        this.getUser = getUser;
    }

    public void updateUser(Long id, UserRequestDto userRequestDto) {

        var userSalved = getUser.getUserById(id);

        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }

        User user = UserGatewayMapper.fromUserRequestDtoToUser(userRequestDto);

        updateUser.updateUser(id, user);
    }

    public void updatePassword(Long id, String password) {

        var userSalved = getUser.getUserById(id);

        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }

        updateUser.updatePassword(id, password);
    }
}
