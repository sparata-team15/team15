package com.sparta.team15.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardRequestDto {
    private String author;

    @NotNull
    private Long columnId;

    @NotNull
    private String content;

    private String description;

    private Date date;
}
