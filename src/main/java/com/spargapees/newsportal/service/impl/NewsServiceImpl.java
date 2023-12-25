package com.spargapees.newsportal.service.impl;

import com.spargapees.newsportal.models.Comments;
import com.spargapees.newsportal.models.News;
import com.spargapees.newsportal.repository.NewsRepository;
import com.spargapees.newsportal.service.CommentsService;
import com.spargapees.newsportal.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private NewsRepository newsRepository;
    private CommentsService commentsService;
    @Override
    public List<News> getNews() {
        List<News> newsList = newsRepository.findAll();
        Collections.reverse(newsList);
        return newsList;

    }
    @Override
    public News getNewsById(Long id) {
        return newsRepository.getReferenceById(id);
    }

    @Override
    public void deleteNewsById(Long id) {
        News news = newsRepository.findById(id).orElse(null);
        if (news == null) return;
        List<Comments> commentsList = news.getComments();
        for (Comments comments : commentsList) {
            commentsService.deleteCommentById(comments.getId());
        }
        newsRepository.deleteById(news.getId());
    }

    @Override
    public void addCommentToNews(Long id, Comments comments) {
        News news = newsRepository.findById(id).orElse(null);
        if (news == null) return;
        news.getComments().add(comments);
        newsRepository.save(news);
    }

    @Override
    public void deleteCommentFromNews(Long id, Comments comments) {
        News news = newsRepository.findById(id).orElse(null);
        if (news == null) return;
        news.getComments().remove(comments);
        newsRepository.save(news);
    }

    @Override
    public void addNews(News news) {
        newsRepository.save(news);
    }

}
