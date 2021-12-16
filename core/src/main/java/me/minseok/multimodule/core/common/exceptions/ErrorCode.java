package me.minseok.multimodule.core.common.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
  BAD_LOGIN(400, "AU_004", "잘못된 아이디 또는 패스워드입니다.");

  private final String code;
  private final String message;
  private final int status;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
