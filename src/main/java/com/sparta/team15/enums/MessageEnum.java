package com.sparta.team15.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MessageEnum {

  //User 관련 메세지
  LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
  LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다."),
  SIGNUP_SUCCESS(HttpStatus.OK, "회원가입에 성공하였습니다."),
  WITHDRAW_SUCCESS_MESSAGE(HttpStatus.OK, "회원탈퇴에 성공했습니다."),
  REFRESH_TOKEN_SUCCESS_MESSAGE(HttpStatus.OK, "토큰 재발급 성공했습니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
