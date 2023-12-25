package com.spargapees.newsportal.service.impl;

import com.spargapees.newsportal.models.Comments;
import com.spargapees.newsportal.repository.CommentsRepository;
import com.spargapees.newsportal.service.CommentsService;
import com.spargapees.newsportal.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private CommentsRepository commentsRepository;


    @Override
    public void addComment(Comments comments) {
        //newsService.addCommentToNews(comments.getNews().getId(), comments);
        commentsRepository.save(comments);
    }

    @Override
    public void deleteCommentById(Long id) {
        Comments comments = commentsRepository.getReferenceById(id);
        if (comments != null) {
            //newsService.deleteCommentFromNews(comments.getNews().getId(), comments);
            commentsRepository.deleteById(comments.getId());
        }
    }

}
