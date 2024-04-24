package com.example.demo.service;

import com.example.demo.model.News;
import com.example.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News save(News news) {
        return newsRepository.save(news);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }
}
