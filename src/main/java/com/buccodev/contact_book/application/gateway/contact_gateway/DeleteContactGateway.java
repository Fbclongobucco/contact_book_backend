package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.usecases.contacts_usecases.DeleteContact;
import com.buccodev.contact_book.application.usecases.contacts_usecases.GetContact;

public class DeleteContactGateway {

    private final DeleteContact deleteContact;
    private final GetContact getContact;

    public DeleteContactGateway(DeleteContact deleteContact, GetContact getContact) {
        this.deleteContact = deleteContact;
        this.getContact = getContact;
    }

    public void deleteContact(Long idContact) {

        var contact = getContact.getContactById(idContact);

        if (contact == null) {
            throw new RuntimeException("Contact not found");
        }

        deleteContact.deleteContact(idContact);
    }

}
