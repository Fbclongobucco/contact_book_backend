package com.buccodev.contact_book.infrastructure.repositories.entities_respository;

import com.buccodev.contact_book.infrastructure.repositories.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {
}
