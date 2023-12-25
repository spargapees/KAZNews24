package com.spargapees.newsportal.service;

import com.spargapees.newsportal.Dtos.UserInputDto;
import com.spargapees.newsportal.models.User;

public interface UserService {
    public User getUserById(Long id);

}
