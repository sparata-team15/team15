package com.sparta.team15.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String content;
    private Long userId;
    private Long cardId;
}