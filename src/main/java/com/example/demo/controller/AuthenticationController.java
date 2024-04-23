package com.example.demo.controller;

import com.example.demo.user.AuthenticationResponse;
import com.example.demo.user.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AuthenticationService;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    public AuthenticationController(AuthenticationService authService){
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    

    

}
