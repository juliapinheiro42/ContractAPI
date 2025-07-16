package com.contactpro.contactpro.controller;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactpro.contactpro.dto.auth.AuthRequest;
import com.contactpro.contactpro.dto.auth.AuthResponse;
import com.contactpro.contactpro.model.Profile;
import com.contactpro.contactpro.model.User;
import com.contactpro.contactpro.repository.UserRepository;
import com.contactpro.contactpro.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            String token = tokenProvider.generateToken(
                    user.getUsername(),
                    List.of(user.getProfile().name())
            );

            return new AuthResponse(token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais inválidas");
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Usa o perfil fornecido ou padrão OPERADOR
        user.setProfile(request.getProfile() != null ? request.getProfile() : Profile.OPERADOR);

        userRepository.save(user);

        return "Usuário registrado com sucesso com perfil: " + user.getProfile();
    }
}
