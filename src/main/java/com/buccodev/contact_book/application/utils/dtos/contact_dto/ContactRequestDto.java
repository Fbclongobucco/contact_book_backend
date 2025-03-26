package com.buccodev.contact_book.application.utils.dtos.contact_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactRequestDto(
                                @NotBlank(message = "Name is required!")
                                @Size(min = 3, max = 150, message = "Name must be at least 3 characters and less than 150!")
                                String name,
                                @NotBlank(message = "Number is required!")
                                @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number!")
                                String number,
                                String contactURLPhoto) {
}
