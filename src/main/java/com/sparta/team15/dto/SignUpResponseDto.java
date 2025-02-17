package com.sparta.team15.dto;

import com.sparta.team15.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private String username;
    private String name;
    private String password;

    public SignUpResponseDto(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.password = user.getPassword();
    }
}
