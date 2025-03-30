package com.buccodev.contact_book.infrastructure.services.contact_service;

import com.buccodev.contact_book.application.usecases.contacts_usecases.DeleteContact;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.ContactEntityRepository;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactService implements DeleteContact {

    private final ContactEntityRepository contactRepository;

    public DeleteContactService(ContactEntityRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    @Override
    public void deleteContact(Long idContact) {
        var contact = contactRepository.findById(idContact).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));

        contactRepository.deleteById(contact.getId());
    }
}
