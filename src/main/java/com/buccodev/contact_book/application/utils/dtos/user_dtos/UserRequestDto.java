package com.buccodev.contact_book.application.utils.dtos.user_dtos;

import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserRequestDto(
                             @NotBlank(message = "Name is required!")
                             String name,
                             @Email(message = "Email is not valid!")
                             @NotBlank(message = "Email is required!")
                             String email,
                             @NotBlank(message = "Password is required!")
                             @Size(min = 6, message = "Password must be at least 6 characters!")
                             String password,
                             Set<ContactRequestDto> contactDtos) {
}
