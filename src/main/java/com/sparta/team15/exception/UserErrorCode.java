package com.sparta.team15.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
        "해당 유저가 없습니다."),
    PW_MISMATCH(HttpStatus.NOT_FOUND,
        "유저이름과 비밀번호 불일치"),
    DUPLICATED_USER(HttpStatus.CONFLICT,
        "중복된 유저입니다."),
    USER_NOT_MATCH(HttpStatus.NOT_FOUND,
        "유저가 일치하지 않습니다."),
    PATTERN_NOT_MATCH(HttpStatus.NOT_FOUND,
        "비밀번호 패턴이 일치하지 않습니다."),
    WITHDRAW_USER(HttpStatus.NOT_FOUND,
        "탈퇴한 회원입니다."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.NOT_FOUND,
        "REFRESH_TOKEN 값이 일치 하지 않습니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST,
        "비밀번호가 일치하지 않습니다."),
    NOT_MANAGER(HttpStatus.NOT_FOUND,
        "MANAGER TOKEN값이 일치하지 않습니다."),

    // Board
    NOT_FOUND_BOARD(HttpStatus.NOT_FOUND,
        "해당 보드는 존재하지 않습니다."),
    NOT_AUTHORIZATION_ABOUT_BOARD(HttpStatus.FORBIDDEN,
        "해당 보드에 대한 권한이 없습니다."),
    ALREADY_INVITED_USER(HttpStatus.CONFLICT,
        "이미 초대된 유저 입니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST,
        "유효하지 않는 변경 요청 값 입니다."),
    NOT_FOUND_WORKER_IN_BOARD(HttpStatus.NOT_FOUND,
        "해당 작업자가 존재하지 않습니다."),
    NOT_ACCEPTABLE_TO_MAKE_BOARD(HttpStatus.NOT_ACCEPTABLE,
        "보드를 만들 수 없습니다."),

    // Card
    NOT_FOUND_CARD(HttpStatus.NOT_FOUND,
                   "존재하지 않는 카드입니다."),
    NOT_AUTHORIZATION_ABOUT_CARD(HttpStatus.FORBIDDEN,
                   "해당 카드에 대한 권한이 없습니다.");


    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.status;
    }
}