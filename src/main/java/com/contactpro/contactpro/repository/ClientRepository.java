package com.contactpro.contactpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactpro.contactpro.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByDocument(String document);
}
