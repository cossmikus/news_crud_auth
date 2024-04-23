//package com.example.demo.user;
//
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        return userOptional.orElse(null);
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        Optional<User> existingUserOptional = userRepository.findById(id);
//        if (existingUserOptional.isPresent()) {
//            User existingUser = existingUserOptional.get();
//            existingUser.setName(user.getName());
//            existingUser.setUsername(user.getUsername());
//            return userRepository.save(existingUser);
//        } else {
//            return null; // Or handle the case as needed (e.g., throw exception)
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (userOptional.isPresent()) {
//            userRepository.deleteById(id);
//            return "User deleted successfully";
//        } else {
//            return "User not found";
//        }
//    }
//}
