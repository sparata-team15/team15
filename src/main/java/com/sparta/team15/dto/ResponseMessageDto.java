package com.sparta.team15.dto;

import com.sparta.team15.enums.MessageEnum;

public class ResponseMessageDto {
  private int status;
  private String message;

  public ResponseMessageDto(MessageEnum status) {
    this.status = status.getHttpStatus().value();
    this.message = status.getMessage();
  }
}
