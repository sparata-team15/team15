package com.sparta.team15.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    // NotNull 등 validation 애노테이션

    private String content;
    private Long userId;
    private Long cardId;
}