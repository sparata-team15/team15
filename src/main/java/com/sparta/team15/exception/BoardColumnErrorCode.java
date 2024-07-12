package com.sparta.team15.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardColumnErrorCode implements ErrorCode {

    NO_AUTHENTICATION(HttpStatus.NOT_FOUND,
        "컬럼 관리 권한이 없습니다."),
    DUPLICATED_COLUMN_NAME(HttpStatus.LOCKED,
            "이미 존재하는 컬럼 이름입니다."),
    NOT_FOUND_COLUMN(HttpStatus.NOT_FOUND,
            "존재하지 않는 컬럼입니다."),
    NOT_TEAM_MEMBER(HttpStatus.LOCKED,
            "팀에 속해있지 않습니다.")
    ;


    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.status;
    }
}