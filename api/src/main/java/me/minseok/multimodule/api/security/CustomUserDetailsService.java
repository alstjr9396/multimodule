package me.minseok.multimodule.api.security;

import lombok.RequiredArgsConstructor;
import me.minseok.multimodule.core.user.domain.User;
import me.minseok.multimodule.core.user.domain.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() ->
        new UsernameNotFoundException("유저를 찾을 수 없습니다. email : " + email));

    return new UserPrincipal(user);
  }
}
