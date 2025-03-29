package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.gateway.exception.ContactDuplicateException;
import com.buccodev.contact_book.application.usecases.contacts_usecases.GetContact;
import com.buccodev.contact_book.application.usecases.contacts_usecases.RegisterContact;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactRequestDto;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactResponseDto;
import com.buccodev.contact_book.application.utils.mappers.ContactGatewayMapper;
import com.buccodev.contact_book.core.domain.Contact;

public class RegisterContactGateway {

    private final RegisterContact registerContact;
    private final GetContact getContact;

    public RegisterContactGateway(RegisterContact registerContact, GetContact getContact) {
        this.registerContact = registerContact;
        this.getContact = getContact;
    }

    public ContactResponseDto registerContact(Long userId, ContactRequestDto contactRequestDto) {

        if (getContact.existsContactByNameAndNumber(contactRequestDto.name(), contactRequestDto.number())) {
            throw new ContactDuplicateException("Contact already exists");
        }

        var contact = ContactGatewayMapper.fromContactRequestDtoToContact(contactRequestDto);

        var contactSaved =  registerContact.registerContact(userId, contact);

        return ContactGatewayMapper.fromContactToContactResponseDto(contactSaved);
    }

}
