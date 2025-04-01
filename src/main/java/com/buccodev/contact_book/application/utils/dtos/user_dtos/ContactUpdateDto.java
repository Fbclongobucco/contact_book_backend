package com.buccodev.contact_book.application.utils.dtos.user_dtos;

import jakarta.validation.constraints.Pattern;

public record ContactUpdateDto(String name, @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number!") String number) {
}
