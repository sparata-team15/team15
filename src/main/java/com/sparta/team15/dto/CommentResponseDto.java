package com.sparta.team15.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private Long userId;
    private Long cardId;
}
