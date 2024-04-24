package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

// Import your service classes
import com.example.demo.service.ApplicationService;
import com.example.demo.service.NewsService;
import com.example.demo.service.UserService;
// Import your model classes
import com.example.demo.model.Application;
import com.example.demo.model.News;
import com.example.demo.model.User;

@RestController
public class DemoController {

    // Autowire your service objects
    private final ApplicationService applicationService;
    private final NewsService newsService;
    private UserService userService;

    // Constructor for autowiring service beans
    public DemoController(ApplicationService applicationService, NewsService newsService, UserService userService) {
        this.applicationService = applicationService;
        this.newsService = newsService;
        this.userService = userService;
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    @PostMapping("/applications")
    public ResponseEntity<String> submitApplication(@RequestBody Application application) {
        applicationService.save(application);
        return ResponseEntity.ok("Application submitted successfully!");
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.findAll();
        return ResponseEntity.ok(applications);
    }

    @PostMapping("/news")
    public ResponseEntity<String> postNews(@RequestBody News news) {
        newsService.save(news);
        return ResponseEntity.ok("News posted successfully!");
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.findAll();
        return ResponseEntity.ok(newsList);
    }

    //get for user
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
