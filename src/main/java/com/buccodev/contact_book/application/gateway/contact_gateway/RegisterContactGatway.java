package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.usecases.contacts_usecases.RegisterContact;
import com.buccodev.contact_book.core.domain.Contact;

public class RegisterContactGatway {

    private final RegisterContact registerContact;

    public RegisterContactGatway(RegisterContact registerContact) {
        this.registerContact = registerContact;
    }

    public Contact registerContact(Long userId, Contact contact) {
        return registerContact.registerContact(userId, contact);
    }


}
