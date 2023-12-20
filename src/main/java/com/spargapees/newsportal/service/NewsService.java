package com.spargapees.newsportal.service;

import com.spargapees.newsportal.models.Comments;
import com.spargapees.newsportal.models.News;

import java.util.List;

public interface NewsService {
    public List<News> getNews();
    public News getNewsById(Long id);
    public void addCommentToNews(Long id, Comments comments);
}
