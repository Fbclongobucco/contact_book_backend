package com.buccodev.contact_book.application.utils.dtos.user_dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordDto(
                                @NotBlank
                                @Size(min = 6, message = "Password must be at least 6 characters!")
                                String newPassword) {

}
