package com.sparta.team15.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardColumnOrderRequestDto {
    @NotNull(message = "보드의 위치를 입력해주세요.")
    private Double position;
}
