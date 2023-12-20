package com.spargapees.newsportal.controllers;

import com.spargapees.newsportal.models.Comments;
import com.spargapees.newsportal.models.News;
import com.spargapees.newsportal.models.User;
import com.spargapees.newsportal.service.CommentsService;
import com.spargapees.newsportal.service.NewsService;
import com.spargapees.newsportal.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@AllArgsConstructor
public class CommentController {
    private CommentsService commentsService;
    private UserService userService;
    private NewsService newsService;

    public static Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
    @PostMapping("/addComment")
    public String addComment(@RequestParam("comment") String comment,
                             @RequestParam("id") Long id, HttpSession session, Model model) {

        News news = newsService.getNewsById(id);

        Comments comments = new Comments();
        comments.setComment(comment);
        comments.setNews(news);
        comments.setPost_date(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        comments.setUser(userService.getUserById(convertToLong(session.getAttribute("currentUser"))));

        commentsService.addComment(comments);
        model.addAttribute("news", news);
        return String.format("redirect:/%d", news.getId());
    }

}
