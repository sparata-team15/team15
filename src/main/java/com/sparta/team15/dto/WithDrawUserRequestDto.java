package com.sparta.team15.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class WithDrawUserRequestDto {
  @NotBlank(message = "비밀번호 입력하세요")
  String password;
}
