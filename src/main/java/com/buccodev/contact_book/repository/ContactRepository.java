package com.buccodev.contact_book.repository;

import com.buccodev.contact_book.entities.Contact;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByUsersId(Long userId, Pageable pageable);
}
