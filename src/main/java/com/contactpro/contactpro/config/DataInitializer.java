package com.contactpro.contactpro.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.contactpro.contactpro.model.Profile;
import com.contactpro.contactpro.model.User;
import com.contactpro.contactpro.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder().username("admin").password(encoder.encode("admin123")).profile(Profile.ADMIN).build();
                userRepository.save(admin);
                System.out.println("Admin user criado.");
            }
        };
    }
}
