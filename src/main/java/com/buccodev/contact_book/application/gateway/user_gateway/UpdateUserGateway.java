package com.buccodev.contact_book.application.gateway.user_gateway;

import com.buccodev.contact_book.application.gateway.exception.UserNotFoundException;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.usecases.user_usecases.UpdateUser;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UpdatePasswordDto;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserUpdateDto;


public class UpdateUserGateway {

    private final UpdateUser updateUser;
    private final GetUser getUser;

    public UpdateUserGateway(UpdateUser updateUser, GetUser getUser) {
        this.updateUser = updateUser;
        this.getUser = getUser;
    }

    public void updateUser(Long id, UserUpdateDto userUpdateDto) {

        var userSalved = getUser.getUserById(id);

        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }

        if(userUpdateDto.name() != null) {
            userSalved.setName(userUpdateDto.name());
        }

        if(userUpdateDto.email() != null) {
            userSalved.setEmail(userUpdateDto.email());
        }

        updateUser.updateUser(id, userSalved);
    }

    public void updatePassword(Long id, UpdatePasswordDto updatePasswordDto) {

        var userSalved = getUser.getUserById(id);

        if(userSalved == null) {
            throw new UserNotFoundException("User not found");
        }

        updateUser.updatePassword(id, updatePasswordDto.newPassword());
    }
}
