package com.buccodev.contact_book.infrastructure.repositories.entities_respository;

import com.buccodev.contact_book.infrastructure.repositories.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {

    List<ContactEntity> findAllByUserEntity_Id(Long userId);

    Optional<ContactEntity> findByName(String name);

    Optional<ContactEntity> findByNumber(String number);

    boolean existsByNameAndNumber(String number, String name);


}
