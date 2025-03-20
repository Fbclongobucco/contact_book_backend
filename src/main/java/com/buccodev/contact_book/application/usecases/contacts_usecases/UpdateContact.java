package com.buccodev.contact_book.application.usecases.contacts_usecases;

import com.buccodev.contact_book.core.domain.Contact;

public interface UpdateContact {

    void updateContact(Long idContact, Contact contact);

}
