package com.buccodev.contact_book.application.utils.mappers;

import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserRequestDto;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.UserResponseDto;
import com.buccodev.contact_book.core.domain.User;

public class UserMapper {

    public static User fromRequestDtoToUser(UserRequestDto userRequestDto) {
        return new User(
                null,
                userRequestDto.name(),
                userRequestDto.email(),
                userRequestDto.password()
        );
    }

    public static UserResponseDto fromUserToResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
