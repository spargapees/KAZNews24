package com.spargapees.newsportal.Dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInputDto {
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
