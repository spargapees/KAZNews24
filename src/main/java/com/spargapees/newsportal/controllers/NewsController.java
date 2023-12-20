package com.spargapees.newsportal.controllers;

import com.spargapees.newsportal.models.Comments;
import com.spargapees.newsportal.models.News;
import com.spargapees.newsportal.models.User;
import com.spargapees.newsportal.service.NewsService;
import com.spargapees.newsportal.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;
    private UserService userService;

    public static Long convertToLong(Object o){
        if (o == null) return 0L;
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
    @GetMapping("/")
    public String getNews(Model model, HttpSession session) {
        model.addAttribute("news", newsService.getNews());
        model.addAttribute("user", userService.getUserById(convertToLong(session.getAttribute("currentUser"))));
        return "news";
    }

    @GetMapping(value = "/{id}")
    public String getNewsById(@PathVariable Long id,  Model model, HttpSession session) {
        News news = newsService.getNewsById(id);
        if (news == null) return "redirect:/";
        model.addAttribute("news", news);
        model.addAttribute("user", userService.getUserById(convertToLong(session.getAttribute("currentUser"))));

        return "newsDetails";
    }

}
