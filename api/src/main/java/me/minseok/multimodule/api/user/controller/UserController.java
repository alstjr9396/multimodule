package me.minseok.multimodule.api.user.controller;

import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.minseok.multimodule.core.user.dto.SignUpRequest;
import me.minseok.multimodule.core.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<Void> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    Long userId = userService.registerUser(signUpRequest);
    return ResponseEntity.created(URI.create("/api/user/" + userId)).build();

  }
}
