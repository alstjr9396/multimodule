package me.minseok.multimodule.api.config.security;

import lombok.RequiredArgsConstructor;
import me.minseok.multimodule.api.security.CustomAuthenticationEntryPoint;
import me.minseok.multimodule.api.security.CustomUserDetailsService;
import me.minseok.multimodule.api.security.LoginFilter;
import me.minseok.multimodule.api.security.LoginSuccessHandler;
import me.minseok.multimodule.api.security.TokenAuthenticationFilter;
import me.minseok.multimodule.api.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String ADMIN = "ADMIN";
  private final TokenProvider tokenProvider;
  private final CustomUserDetailsService customUserDetailsService;

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter(tokenProvider, customUserDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .headers()
        .frameOptions()
        .disable();

    http
        .cors()
        .and()
        .csrf()
        .disable()
        .formLogin()
        .disable()
        .httpBasic()
        .disable();

    http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http
        .exceptionHandling()
        .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

    http
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users").hasRole(ADMIN)
        .anyRequest().permitAll();

    http
        .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);

  }

  private LoginFilter loginFilter() throws Exception {
    LoginFilter loginFilter = new LoginFilter();
    loginFilter.setFilterProcessesUrl("/api/user/login");
    loginFilter.setAuthenticationManager(authenticationManagerBean());
    loginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler(tokenProvider));
    return loginFilter;
  }
}
