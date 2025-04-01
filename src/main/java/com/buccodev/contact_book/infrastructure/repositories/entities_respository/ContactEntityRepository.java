package com.buccodev.contact_book.infrastructure.repositories.entities_respository;

import com.buccodev.contact_book.infrastructure.repositories.entities.ContactEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {

    Page<ContactEntity> findAllByUserEntity_Id(Long userId, Pageable pageable);

    Optional<ContactEntity> findByName(String name);

    Optional<ContactEntity> findByNumber(String number);

    boolean existsByNameAndNumber(String number, String name);


}
