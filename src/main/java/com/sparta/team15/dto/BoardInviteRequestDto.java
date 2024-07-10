package com.sparta.team15.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardInviteRequestDto {

    @NotBlank
    private Long userId;

}
