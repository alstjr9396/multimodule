package me.minseok.multimodule.api.common.dto;

import lombok.Getter;
import me.minseok.multimodule.core.common.exceptions.ErrorCode;

@Getter
public class ErrorResponse {
  private String message;
  private String code;

  private ErrorResponse(ErrorCode code) {
    this.message = code.getMessage();
    this.code = code.getCode();
  }

  public static ErrorResponse of(ErrorCode code) {
    return new ErrorResponse(code);
  }
}
