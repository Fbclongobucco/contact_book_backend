package com.buccodev.contact_book.application.usecases.contacts_usecases;

import com.buccodev.contact_book.core.domain.Contact;

public interface RegisterContact {

    Contact registerContact(Long userId, Contact contact);

    Boolean existsContactByNameAndNumber(String name, String number);

}
