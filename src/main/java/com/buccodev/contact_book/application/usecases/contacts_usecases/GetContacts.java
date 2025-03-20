package com.buccodev.contact_book.application.usecases.contacts_usecases;

import com.buccodev.contact_book.core.domain.Contact;

import java.util.List;

public interface GetContacts {

    List<Contact> getAllContactsByUser(Long userId, Integer page, Integer size);

    Contact getContactById(Long id);

    Contact getContactByName(String name);

    Contact getContactByNumber(String number);
}
