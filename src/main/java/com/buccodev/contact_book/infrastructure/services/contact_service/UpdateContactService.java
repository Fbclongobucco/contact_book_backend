package com.buccodev.contact_book.infrastructure.services.contact_service;

import com.buccodev.contact_book.application.usecases.contacts_usecases.UpdateContact;
import com.buccodev.contact_book.core.domain.Contact;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.ContactEntityRepository;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateContactService implements UpdateContact {

    private final ContactEntityRepository contactRepository;

    public UpdateContactService(ContactEntityRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    @Override
    public void updateContact(Long idContact, Contact contact) {
        var contactEntity = contactRepository.findById(idContact).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));

        contactEntity.setName(contact.getName());
        contactEntity.setNumber(contact.getNumber());

        contactRepository.save(contactEntity);
    }

    @Transactional
    @Override
    public void updateContactPhoto(Long idContact, String contactPhotoUrl) {
        var contactEntity = contactRepository.findById(idContact).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));

        contactEntity.setContactPhoto(contactPhotoUrl);

        contactRepository.save(contactEntity);


    }
}
