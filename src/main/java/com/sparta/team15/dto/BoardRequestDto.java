package com.sparta.team15.dto;

import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.User;
import lombok.Getter;

@Getter
public class BoardRequestDto {

    private String title;
    private String description;

    public Board toEntity(User createdBy) {
        return Board.builder().title(title).build();
    }
}
