package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.usecases.contacts_usecases.GetContact;
import com.buccodev.contact_book.core.domain.Contact;

import java.util.List;

public class GetContactGateway {

    private final GetContact getContact;

    public GetContactGateway(GetContact getContact) {
        this.getContact = getContact;
    }

    public List<Contact> getAllContactsByUser(Long userId, Integer page, Integer size) {
        return getContact.getAllContactsByUser(userId, page, size);
    }

    public Contact getContactById(Long id) {
        return getContact.getContactById(id);
    }

    public Contact getContactByName(String name) {
        return getContact.getContactByName(name);
    }

    public Contact getContactByNumber(String number) {
        return getContact.getContactByNumber(number);
    }
}
