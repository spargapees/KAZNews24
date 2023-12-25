package com.spargapees.newsportal.service;

import com.spargapees.newsportal.Dtos.UserInputDto;
import com.spargapees.newsportal.models.User;

public interface AuthService {
    public void addUser(User user);
    public User inputUser(UserInputDto input);
    public User loginAdmin(UserInputDto input);
}
