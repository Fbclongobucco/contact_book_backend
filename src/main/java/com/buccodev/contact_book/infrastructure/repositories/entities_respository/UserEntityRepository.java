package com.buccodev.contact_book.infrastructure.repositories.entities_respository;

import com.buccodev.contact_book.infrastructure.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);


    Optional<UserEntity> findByNameAndEmail(String name, String email);


    boolean existsById(Long id);

}
