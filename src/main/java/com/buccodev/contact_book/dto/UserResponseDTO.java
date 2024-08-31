package com.buccodev.contact_book.dto;

import java.util.List;

public record UserResponseDTO(String name, List<ContactDTO> contacts) {
}
