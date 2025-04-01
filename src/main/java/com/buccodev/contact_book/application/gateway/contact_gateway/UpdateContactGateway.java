package com.buccodev.contact_book.application.gateway.contact_gateway;

import com.buccodev.contact_book.application.gateway.exception.ContactNotFoundException;
import com.buccodev.contact_book.application.usecases.contacts_usecases.GetContact;
import com.buccodev.contact_book.application.usecases.contacts_usecases.UpdateContact;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactPhotoUrlDto;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactRequestDto;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.ContactUpdateDto;
import com.buccodev.contact_book.application.utils.mappers.ContactGatewayMapper;

public class UpdateContactGateway {

    private final UpdateContact updateContact;
    private final GetContact getContact;

    public UpdateContactGateway(UpdateContact updateContact, GetContact getContact) {
        this.updateContact = updateContact;
        this.getContact = getContact;
    }

    public void updateContact(Long idContact, ContactUpdateDto contactUpdateDto) {

        var contact = getContact.getContactById(idContact);

        if (contact == null) {
            throw new ContactNotFoundException("Contact not found");
        }

        if(contactUpdateDto.name() != null) {
            contact.setName(contactUpdateDto.name());
        }

        if(contactUpdateDto.number() != null) {
            contact.setNumber(contactUpdateDto.number());
        }
        updateContact.updateContact(idContact, contact);
    }

    public void updateContactPhoto(Long idContact, ContactPhotoUrlDto contactPhotoUrl) {
        var contact = getContact.getContactById(idContact);

        if (contact == null) {
            throw new ContactNotFoundException("Contact not found");
        }
        updateContact.updateContactPhoto(idContact, contactPhotoUrl.contactURLPhoto());
    }
}
