package com.spargapees.newsportal.controllers;

import com.spargapees.newsportal.Dtos.UserInputDto;
import com.spargapees.newsportal.models.User;
import com.spargapees.newsportal.service.AuthService;
import com.spargapees.newsportal.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    private UserService userService;

    @GetMapping(value = "/sign-in")
    public String singIn(Model model) {
        model.addAttribute("user", new UserInputDto());
        return "signIn";
    }

    @PostMapping(value = "/sign-in")
    public String signIn(@ModelAttribute("user") UserInputDto input, HttpSession session) {
        User user = authService.inputUser(input);

        if (user == null) {
            return "redirect:/sign-in";
        }

        session.setAttribute("currentUser", user.getId());
        return "redirect:/";
    }

    @GetMapping(value = "sign-up")
    public String singUp(Model model) {
        model.addAttribute("user", new User());
        return "singUp";
    }
    @PostMapping(value = "sing-up")
    public String signUp(@ModelAttribute("user") User user, Model model) {
        authService.addUser(user);
        model.addAttribute("username", user.getFullname());
        return "redirect:/";
    }

    @GetMapping(value = "/log-out")
    public String logOut(HttpSession session, Model model) {
        session.setAttribute("currentUser", null);
        model.addAttribute("user", new UserInputDto());
        return "signIn";
    }
}
