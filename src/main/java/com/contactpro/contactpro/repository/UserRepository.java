package com.contactpro.contactpro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactpro.contactpro.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
