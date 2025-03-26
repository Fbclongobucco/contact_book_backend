package com.buccodev.contact_book.application.utils.dtos.user_dtos;

import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactResponseDto;

import java.util.Set;

public record UserResponseDto(Long id, String name, String email) {
}
