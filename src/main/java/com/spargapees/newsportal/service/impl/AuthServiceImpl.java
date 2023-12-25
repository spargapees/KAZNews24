package com.spargapees.newsportal.service.impl;

import com.spargapees.newsportal.Dtos.UserInputDto;
import com.spargapees.newsportal.models.User;
import com.spargapees.newsportal.repository.UserRepository;
import com.spargapees.newsportal.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;

    private String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add input string bytes to digest
            md.update(password.getBytes());

            // Get the hash bytes
            byte[] bytes = md.digest();

            // Convert the byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle exception (e.g., print error or throw a RuntimeException)
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void addUser(User user) {
        user.setRole_id(1);
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User inputUser(UserInputDto input) {
        User user = userRepository.findByEmail(input.getEmail());
        if (user == null || !Objects.equals(user.getPassword(), hashPassword(input.getPassword())) ) {
            return null;
        }

        return user;
    }

    @Override
    public User loginAdmin(UserInputDto input) {
        User user = userRepository.findByEmail(input.getEmail());
        System.out.println(user);
        System.out.println(user.getPassword());
        System.out.println(user.getRole_id());
        if (user == null || !Objects.equals(user.getPassword(), hashPassword(input.getPassword())) || user.getRole_id() != 2) {
            return null;
        }

        return user;
    }
}
