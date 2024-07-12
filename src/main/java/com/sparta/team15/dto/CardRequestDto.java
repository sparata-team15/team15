package com.sparta.team15.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CardRequestDto {
    private String author;

    @NotNull
    private Long columnId;

    @NotNull
    private String content;

    private String description;

    private int position;

    private Date date;
}
