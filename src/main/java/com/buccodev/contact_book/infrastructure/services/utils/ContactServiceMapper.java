package com.buccodev.contact_book.infrastructure.services.utils;

import com.buccodev.contact_book.core.domain.Contact;
import com.buccodev.contact_book.core.domain.User;
import com.buccodev.contact_book.infrastructure.repositories.entities.ContactEntity;
import com.buccodev.contact_book.infrastructure.repositories.entities.UserEntity;

public class ContactServiceMapper {

    public static Contact fromContactEntityToContact(ContactEntity contactEntity) {

        var userEntity = contactEntity.getUserEntity();
        var user = UserServiceMapper.fromUserEntityToUser(userEntity);

        var contact = new Contact(
                contactEntity.getId(),
                contactEntity.getName(),
                contactEntity.getNumber(),
                user);
        contact.setContactPhoto(contactEntity.getContactPhoto());

        return contact;
    }
}
