package com.spargapees.newsportal.service;

import com.spargapees.newsportal.models.Comments;

public interface CommentsService {
    public void addComment(Comments comments);
    public void deleteCommentById(Long id);
}
