package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.gateway.exception.ContactNotFoundException;
import com.buccodev.contact_book.application.gateway.exception.UserNotFoundException;
import com.buccodev.contact_book.application.usecases.contacts_usecases.GetContact;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactResponseDto;
import com.buccodev.contact_book.application.utils.mappers.ContactMapper;
import com.buccodev.contact_book.core.domain.Contact;

import java.util.List;

public class GetContactGateway {

    private final GetContact getContact;
    private final GetUser getUser;

    public GetContactGateway(GetContact getContact, GetUser getUser) {
        this.getContact = getContact;
        this.getUser = getUser;
    }

    public List<ContactResponseDto> getAllContactsByUser(Long userId, Integer page, Integer size) {
        var user = getUser.getUserById(userId);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        var contacts = getContact.getAllContactsByUser(userId, page, size);

        return contacts.stream().map(ContactMapper::fromContactToContactResponseDto).toList();
    }

    public ContactResponseDto getContactById(Long id) {
        var contact = getContact.getContactById(id);

        if (contact == null) {
            throw new ContactNotFoundException("Contact not found");
        }
        return ContactMapper.fromContactToContactResponseDto(contact);
    }

    public ContactResponseDto getContactByName(String name) {

        var contact = getContact.getContactByName(name);

        if (contact == null) {
            throw new ContactNotFoundException("Contact not found");
        }

        return ContactMapper.fromContactToContactResponseDto(contact);
    }

    public ContactResponseDto getContactByNumber(String number) {

        var contact = getContact.getContactByNumber(number);

        if (contact == null) {
            throw new ContactNotFoundException("Contact not found");
        }

        return ContactMapper.fromContactToContactResponseDto(contact);
    }
}
