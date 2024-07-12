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
    REFRESH_TOKEN_SUCCESS_MESSAGE(HttpStatus.OK, "토큰 재발급 성공했습니다."),

    //boards
    BOARDS_CREATE_SUCCESS(HttpStatus.OK, "보드를 생성하였습니다."),
    BOARDS_UPDATE_SUCCESS(HttpStatus.OK, "보드를 수정하였습니다."),
    BOARDS_READ_SUCCESS(HttpStatus.OK, "보드를 조회하였습니다."),
    BOARDS_DELETE_SUCCESS(HttpStatus.OK, "보드를 삭제하였습니다."),
    BOARDS_INVITE_SUCCESS(HttpStatus.OK, "보드에 초대하였습니다."),

    // 댓글
    COMMENT_CREATED(HttpStatus.CREATED, "댓글이 생성되었습니다."),
    COMMENT_UPDATED(HttpStatus.OK, "댓글이 수정되었습니다."),
    COMMENT_DELETED(HttpStatus.OK, "댓글이 삭제되었습니다."),
    INVALID_COMMENT_ID(HttpStatus.NOT_FOUND, "유효하지 않은 댓글 ID입니다."),
    UNAUTHORIZED_ACTION(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    INVALID_USER_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 사용자 ID입니다."),
    INVALID_CARD_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 카드 ID입니다."),

    //boardColumn
    COLUMN_CREATED(HttpStatus.OK,"컬럼이 생성되었습니다."),
    COLUMN_DELETED(HttpStatus.OK,"컬럼이 삭제되었습니다."),
    COLUMN_UPDATE_POSITION(HttpStatus.OK,"컬럼이 이동되었습니다."),

    //Card
    CARD_CREATED(HttpStatus.OK,"카드가 생성되었습니다."),
    CARD_UPDATED(HttpStatus.OK,"카드가 수정되었습니다."),
    CARD_DELETED(HttpStatus.OK,"카드가 삭제되었습니다."),
    CARD_UPDATE_POSITION(HttpStatus.OK,"카드가 이동되었습니다."),
    INVALID_COLUMN_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 컬럼입니다."),

    //RefreshToken
    UPDATE_TOKEN_SUCCESS_MESSAGE(HttpStatus.OK,"토큰이 재발급되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
