package com.buccodev.contact_book.infrastructure.services.contact_service;

import com.buccodev.contact_book.application.usecases.contacts_usecases.GetContact;
import com.buccodev.contact_book.core.domain.Contact;
import com.buccodev.contact_book.infrastructure.repositories.entities.ContactEntity;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.ContactEntityRepository;
import com.buccodev.contact_book.infrastructure.repositories.entities_respository.UserEntityRepository;
import com.buccodev.contact_book.infrastructure.services.exceptions.InvalidQueryParametersException;
import com.buccodev.contact_book.infrastructure.services.exceptions.ResourceNotFoundException;
import com.buccodev.contact_book.infrastructure.services.utils.ContactServiceMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetContactService implements GetContact {

    private final ContactEntityRepository  contactRepository;
    private final UserEntityRepository userRepository;

    public GetContactService(ContactEntityRepository repository, UserEntityRepository userRepository) {
        this.contactRepository = repository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Contact> getAllContactsByUser(Long userId, Integer page, Integer size) {

        if(page < 0 || size < 0) {
            throw new InvalidQueryParametersException("Invalid query parameters");
        }

        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
        var pageable = PageRequest.of(page, size);
        var contacts = contactRepository.findAllByUserEntity_Id(userId, pageable);
        return contacts.stream().map(ContactServiceMapper::fromContactEntityToContact).toList();

    }

    @Override
    public Contact getContactById(Long id) {
        var contact = contactRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));
        return ContactServiceMapper.fromContactEntityToContact(contact);
    }

    @Override
    public Contact getContactByName(String name) {
        var contact = contactRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));
        return ContactServiceMapper.fromContactEntityToContact(contact);
    }

    @Override
    public Contact getContactByNumber(String number) {
        var contact = contactRepository.findByNumber(number).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));
        return ContactServiceMapper.fromContactEntityToContact(contact);
    }

    @Override
    public Boolean existsContactByNameAndNumber(String name, String number) {
        return contactRepository.existsByNameAndNumber(name, number);
    }
}
