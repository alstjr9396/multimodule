package me.minseok.multimodule.api.common.advice;

import lombok.extern.slf4j.Slf4j;
import me.minseok.multimodule.api.common.dto.ErrorResponse;
import me.minseok.multimodule.core.common.exceptions.AuthException;
import me.minseok.multimodule.core.common.exceptions.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AuthException.class)
  protected ResponseEntity<ErrorResponse> handleBusinessException(AuthException e) {
    ErrorCode errorCode = e.getErrorCode();
    ErrorResponse response = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
  }
}
