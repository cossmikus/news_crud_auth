package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody User newUser) {
    // Check if user exists
    if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
        return ResponseEntity.badRequest().body("Email already in use!");
    }

    // Check if passwords match
    if (!newUser.getPassword().equals(newUser.getPasswordConfirmation())) {
        return ResponseEntity.badRequest().body("Passwords do not match!");
    }

    // Save new user
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); // Encoding password
    newUser = userRepository.save(newUser);

    return ResponseEntity.ok(newUser);
}

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updatUser (@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            userRepository.findById(id).get();
            userRepository.deleteById(id);
            return "User deleted successfully";
        } catch (Exception e) {
            return "User not found";
        }
    }

}