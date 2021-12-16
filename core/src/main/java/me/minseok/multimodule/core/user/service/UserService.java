package me.minseok.multimodule.core.user.service;

import lombok.RequiredArgsConstructor;
import me.minseok.multimodule.core.user.domain.User;
import me.minseok.multimodule.core.user.domain.UserRepository;
import me.minseok.multimodule.core.user.dto.SignUpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

  private final UserRepository userRepository;

  public Long registerUser(SignUpRequest signUpRequest) {
    User user = User.builder()
        .name(signUpRequest.getName())
        .email(signUpRequest.getEmail())
        .password(signUpRequest.getPassword())
        .build();

    return userRepository.save(user).getId();
  }
}
