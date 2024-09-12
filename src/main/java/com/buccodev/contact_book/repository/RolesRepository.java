package com.buccodev.contact_book.repository;

import com.buccodev.contact_book.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);

}
