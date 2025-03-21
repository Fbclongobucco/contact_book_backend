package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.usecases.contacts_usecases.DeleteContact;

public class DeleteContactGateway {

    private final DeleteContact deleteContact;

    public DeleteContactGateway(DeleteContact deleteContact) {
        this.deleteContact = deleteContact;
    }

    public void deleteContact(Long idContact) {
        deleteContact.deleteContact(idContact);
    }

}
