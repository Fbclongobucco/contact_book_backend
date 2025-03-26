package com.buccodev.contact_book.infrastructure.repositories.entities_respository;

import com.buccodev.contact_book.infrastructure.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
