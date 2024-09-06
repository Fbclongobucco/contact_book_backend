package com.buccodev.contact_book.dto;

import java.util.List;

public record UserResponseDTO(Long id, String name, String email, List<ContactDTO> contacts) {
}
