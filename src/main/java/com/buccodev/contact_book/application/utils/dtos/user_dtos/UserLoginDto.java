package com.buccodev.contact_book.application.utils.dtos.user_dtos;

import jakarta.validation.constraints.Email;

public record UserLoginDto (
        String email,
        String password) {
}
