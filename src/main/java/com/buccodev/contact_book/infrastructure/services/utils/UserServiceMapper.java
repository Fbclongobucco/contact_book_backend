package com.buccodev.contact_book.infrastructure.services.utils;

import com.buccodev.contact_book.core.domain.Contact;
import com.buccodev.contact_book.core.domain.User;
import com.buccodev.contact_book.infrastructure.repositories.entities.ContactEntity;
import com.buccodev.contact_book.infrastructure.repositories.entities.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class UserServiceMapper {

    public static User fromUserEntityToUser(UserEntity userEntity) {
         var user = new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword());
         return user;

    }

}
