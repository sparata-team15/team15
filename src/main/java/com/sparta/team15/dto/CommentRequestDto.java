package com.sparta.team15.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String content;
    @NotNull
    private Long userId;
    @NotNull
    private Long cardId;
}