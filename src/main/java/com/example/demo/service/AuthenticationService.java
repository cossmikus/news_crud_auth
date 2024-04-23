package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.user.AuthenticationResponse;
import com.example.demo.user.User;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenicationManager;

    public AuthenticationService(UserRepository repository, JWTService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenicationManager) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenicationManager = authenicationManager;
    }
    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(user.getRole());

        user = repository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse authenticate(User request){
        authenicationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = repository.findByUsername(request.getUsername());
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }


}
