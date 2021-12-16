package me.minseok.multimodule.core.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;
}
