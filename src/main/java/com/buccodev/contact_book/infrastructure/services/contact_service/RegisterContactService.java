package com.buccodev.contact_book.infrastructure.services.contact_service;

import com.buccodev.contact_book.application.usecases.contacts_usecases.RegisterContact;
import com.buccodev.contact_book.core.domain.Contact;
import com.buccodev.contact_book.infrastructure.repositories.entities.ContactEntity;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.ContactEntityRepository;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.UserEntityRepository;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;
import com.buccodev.contact_book.infrastructure.services.utils.ContactServiceMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class RegisterContactService implements RegisterContact {

    private final ContactEntityRepository contactRepository;
    private final UserEntityRepository userRepository;

    public RegisterContactService(ContactEntityRepository repository, ContactEntityRepository contactRepository, UserEntityRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Contact registerContact(Long userId, Contact contact) {
        var user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        var contactEntity = new ContactEntity(contact);
        contactEntity.setUserEntity(user);
        var contactSaved = contactRepository.save(contactEntity);

        return ContactServiceMapper.fromContactEntityToContact(contactSaved);
    }
}
