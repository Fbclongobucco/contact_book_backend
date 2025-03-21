package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.gateway.exception.ContactDuplicateException;
import com.buccodev.contact_book.application.usecases.contacts_usecases.RegisterContact;
import com.buccodev.contact_book.core.domain.Contact;

public class RegisterContactGateway {

    private final RegisterContact registerContact;

    public RegisterContactGateway(RegisterContact registerContact) {
        this.registerContact = registerContact;
    }

    public Contact registerContact(Long userId, Contact contact) {

        if (registerContact.existsContactByNameAndNumber(contact.getName(), contact.getNumber())) {
            throw new ContactDuplicateException("Contact already exists");
        }
        return registerContact.registerContact(userId, contact);
    }

}
