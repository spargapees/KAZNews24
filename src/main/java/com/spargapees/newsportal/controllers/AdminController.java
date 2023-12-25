package com.spargapees.newsportal.controllers;

import com.spargapees.newsportal.Dtos.UserInputDto;
import com.spargapees.newsportal.models.News;
import com.spargapees.newsportal.models.User;
import com.spargapees.newsportal.service.AuthService;
import com.spargapees.newsportal.service.CommentsService;
import com.spargapees.newsportal.service.NewsService;
import com.spargapees.newsportal.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@AllArgsConstructor
public class AdminController {
    private NewsService newsService;
    private CommentsService commentsService;
    private UserService userService;
    private AuthService authService;
    public static Long convertToLong(Object o){
        if (o == null) return 0L;
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }


    @GetMapping(value = "admin/login")
    public String loginAdmin(Model model) {
        model.addAttribute("user", new UserInputDto());
        return "adminlogin";
    }

    @PostMapping(value = "admin/login")
    public String loginAdmin(@ModelAttribute("user") UserInputDto input, Model model, HttpSession session) {
        User user = authService.loginAdmin(input);
        if (user == null) {
            System.out.println("damn");
            return "redirect:/admin/login";
        }
        session.setAttribute("currentUser", user.getId());
        return "redirect:/anews";
    }

    private boolean adminCheck(HttpSession session, Model model) {
        User user = userService.getUserById(convertToLong(session.getAttribute("currentUser") ) );
        if (user == null || user.getRole_id() == 1) {
            model.addAttribute("news", newsService.getNews());
            model.addAttribute("user", user);
            return false;
        }
        return true;
    }

    @GetMapping(value = "/anews")
    public String allNews(Model model, HttpSession session) {
        if (!adminCheck(session, model))
            return "redirect:/";

        model.addAttribute("newsList", newsService.getNews());
        return "adminnews";
    }

    @PostMapping("/anews/add")
    public String addNews(Model model, HttpSession session,
                          @RequestParam("title") String title,
                          @RequestParam("content") String content){
        if (!adminCheck(session, model))
            return "redirect:/";


        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setTime(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        newsService.addNews(news);
        return "redirect:/anews";
    }

    @GetMapping("anews/{id}")
    public String getNewsDetails(@PathVariable Long id,  Model model, HttpSession session) {
        if (!adminCheck(session, model))
            return "redirect:/";

        News news = newsService.getNewsById(id);
        if (news == null) return "redirect:/anews";
        model.addAttribute("news", news);
        return "adminnewsdetail";
    }
    @PostMapping("anews/delete/{id}")
    public String deleteNews(@PathVariable Long id, Model model, HttpSession session) {
        if (!adminCheck(session, model))
            return "redirect:/";
        newsService.deleteNewsById(id);
        return "redirect:/anews";
    }
    @PostMapping("/anews/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id,  Model model, HttpSession session) {
        if (!adminCheck(session, model))
            return "redirect:/";

        commentsService.deleteCommentById(id);
        return "redirect:/anews";
        //return String.format("redirect:/admin/news/{%d})", model.getAttribute("news"));
    }
}
