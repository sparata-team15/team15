package com.sparta.team15.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardColumnOrderRequestDto {
    private Long columnId;
    private Double position;
}
