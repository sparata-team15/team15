package com.sparta.team15.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MessageEnum {

    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),


    //boards
    BOARDS_CREATE_SUCCESS(HttpStatus.OK, "보드를 생성하였습니다."),
    BOARDS_UPDATE_SUCCESS(HttpStatus.OK, "보드를 수정하였습니다."),
    BOARDS_READ_SUCCESS(HttpStatus.OK, "보드를 조회하였습니다."),
    BOARDS_DELETE_SUCCESS(HttpStatus.OK, "보드를 삭제하였습니다."),
    BOARDS_INVITE_SUCCESS(HttpStatus.OK, "보드에 초대하였습니다."),

    NOT_FOUND_BOARD(HttpStatus.NOT_FOUND, "해당 보드는 존재하지 않습니다."),
    NOT_AUTHORIZATION_ABOUT_BOARD(HttpStatus.FORBIDDEN, "해당 보드에 대한 권한이 없습니다."),
    ALREADY_INVITED_USER(HttpStatus.CONFLICT, "이미 초대된 유저 입니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "유효하지 않는 변경 요청 값 입니다."),
    NOT_FOUND_WORKER_IN_BOARD(HttpStatus.NOT_FOUND, "해당 작업자가 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
