package com.sparta.team15.dto;

import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardRequestDto {

    @NotNull(message = "제목이 필요합니다.")
    private String title;

    @NotNull(message = "설명이 필요합니다.")
    private String description;

    public Board toEntity(User createdBy) {
        return Board.builder().title(title).description(description).build();
    }
}
