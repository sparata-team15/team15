package com.sparta.team15.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MessageEnum {

  LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다.");


  private final HttpStatus httpStatus;
  private final String message;
}
