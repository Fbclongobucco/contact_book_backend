package com.buccodev.contact_book.application.utils.mappers;

import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactRequestDto;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactResponseDto;
import com.buccodev.contact_book.core.domain.Contact;

public class ContactGatewayMapper {

    public static Contact fromContactRequestDtoToContact(ContactRequestDto contactRequestDto) {
        var contact = new Contact(null, contactRequestDto.name(), contactRequestDto.number(), null);
        contact.setContactPhoto(contact.getContactPhoto());
        return contact;
    }

    public static ContactResponseDto fromContactToContactResponseDto(Contact contact) {
        return new ContactResponseDto(contact.getId(),
                contact.getName(),
                contact.getNumber(),
                contact.getContactPhoto());
    }
}
