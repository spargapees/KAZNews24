package com.spargapees.newsportal.service.impl;

import com.spargapees.newsportal.models.Comments;
import com.spargapees.newsportal.models.News;
import com.spargapees.newsportal.repository.NewsRepository;
import com.spargapees.newsportal.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private NewsRepository newsRepository;
    @Override
    public List<News> getNews() {
        return newsRepository.findAll();
    }
    @Override
    public News getNewsById(Long id) {
        return newsRepository.getReferenceById(id);
    }

    @Override
    public void addCommentToNews(Long id, Comments comments) {
        News news = newsRepository.findById(id).orElse(null);
        if (news == null) return;
        news.getComments().add(comments);
    }

}
