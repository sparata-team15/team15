package com.sparta.team15.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardColumnRequestDto {
    @NotNull(message = "보드의 Id를 입력해주세요.")
    private Long boardId;
    @NotBlank(message = "컬럼의 타이틀을 입력해주세요.")
    private String title;
    @NotNull(message = "보드의 위치를 입력해주세요.")
    private Double position;
}
