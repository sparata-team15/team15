package com.sparta.team15.dto;

import com.sparta.team15.enums.MessageEnum;
import lombok.Getter;

@Getter
public class ResponseMessageDto {

    private int status;
    private String message;
    private Object data;

    public ResponseMessageDto(MessageEnum status) {
        this.status = status.getHttpStatus().value();
        this.message = status.getMessage();
    }

    public ResponseMessageDto(MessageEnum messageEnum, Object data) {
        this.status = messageEnum.getHttpStatus().value();
        this.message = messageEnum.getMessage();
        this.data = data;
    }
}
