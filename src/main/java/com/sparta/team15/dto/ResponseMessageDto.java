package com.sparta.team15.dto;

import com.sparta.team15.enums.MessageEnum;
import java.util.List;

public class ResponseMessageDto {
  private int status;
  private String message;
  private List<BoardResponseDto> responseDtoList;

  public ResponseMessageDto(MessageEnum status) {
    this.status = status.getHttpStatus().value();
    this.message = status.getMessage();
  }

  public ResponseMessageDto(MessageEnum messageEnum, List<BoardResponseDto> responseDtoList) {
    this.status = messageEnum.getHttpStatus().value();
    this.message = messageEnum.getMessage();
    this.responseDtoList = responseDtoList;
  }
}
