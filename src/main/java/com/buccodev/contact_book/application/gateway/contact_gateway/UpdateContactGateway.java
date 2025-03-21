package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.usecases.contacts_usecases.UpdateContact;
import com.buccodev.contact_book.core.domain.Contact;

public class UpdateContactGateway {

    private final UpdateContact updateContact;

    public UpdateContactGateway(UpdateContact updateContact) {
        this.updateContact = updateContact;
    }

    public void updateContact(Long idContact, Contact contact) {
        updateContact.updateContact(idContact, contact);
    }

    public void updateContactPhoto(Long idContact, String contactPhotoUrl) {
        updateContact.updateContactPhoto(idContact, contactPhotoUrl);
    }
}
