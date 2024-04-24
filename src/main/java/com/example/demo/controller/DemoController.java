package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    @PostMapping("/application")
    public ResponseEntity<String> submitApplication(@RequestBody Application application) {
        applicationService.save(application);  // You'll need to create this service and corresponding repository
        return ResponseEntity.ok("Application submitted successfully!");
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.findAll();  // You'll need to create this service and corresponding repository
        return ResponseEntity.ok(newsList);
    }

}
