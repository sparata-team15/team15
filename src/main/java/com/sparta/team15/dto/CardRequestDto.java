package com.sparta.team15.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CardRequestDto {
    private Long userId;

    private Long columnId;

    @NotNull
    private String content;

    private String description;

    private Date date;
}
