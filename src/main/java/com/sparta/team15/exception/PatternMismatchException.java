package com.sparta.team15.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PatternMismatchException extends RuntimeException {
  private final ErrorCode errorCode;
}

