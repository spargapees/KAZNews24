package com.spargapees.newsportal.service.impl;

import com.spargapees.newsportal.Dtos.UserInputDto;
import com.spargapees.newsportal.models.User;
import com.spargapees.newsportal.repository.UserRepository;
import com.spargapees.newsportal.service.AuthService;
import com.spargapees.newsportal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
