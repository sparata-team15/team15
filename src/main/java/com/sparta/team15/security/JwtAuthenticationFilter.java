package com.sparta.team15.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.team15.dto.LoginRequestDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.entity.UserRoleEnum;
import com.sparta.team15.entity.UserStatusEnum;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.exception.CommonErrorCode;
import com.sparta.team15.exception.NotFoundException;
import com.sparta.team15.jwt.JwtTokenHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final JwtTokenHelper jwtTokenHelper;

  public JwtAuthenticationFilter(JwtTokenHelper jwtTokenHelper) {
    this.jwtTokenHelper = jwtTokenHelper;
    setFilterProcessesUrl("/login");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    try {
      LoginRequestDto requestDto = new ObjectMapper().readValue(
          request.getInputStream(), LoginRequestDto.class);

      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              requestDto.getUsername(),
              requestDto.getPassword(),
              null
          )
      );

    } catch (IOException e) {
      log.error(e.getMessage());
      throw new NotFoundException(CommonErrorCode.TOKEN_ERROR);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException {

    String username = ((UserDetailsImpl)authResult.getPrincipal()).getUsername();
    UserStatusEnum status = ((UserDetailsImpl)authResult.getPrincipal()).getUser().getStatus();
    UserRoleEnum role = ((UserDetailsImpl)authResult.getPrincipal()).getUser().getRole();

    log.info("username: {}", username);
    log.info("status: {}", status);
    log.info("role: {}", role);

    String accessToken = jwtTokenHelper.createToken(username, status, role);
    String refreshToken = jwtTokenHelper.createRefreshToken();
    response.addHeader(JwtTokenHelper.AUTHORIZATION_HEADER, accessToken);
    response.addHeader(JwtTokenHelper.REFRESH_TOKEN_HEADER, refreshToken);
    jwtTokenHelper.saveRefreshToken(username, refreshToken);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new ObjectMapper()
        .writeValueAsString(new ResponseMessageDto(MessageEnum.LOGIN_SUCCESS.LOGIN_SUCCESS)));
    response.getWriter().flush();

  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed) throws IOException {

    response.setStatus(401);

    response.setCharacterEncoding("utf-8");
    response.getWriter().write("상태 : " + response.getStatus() + ", 로그인 실패");
  }
}