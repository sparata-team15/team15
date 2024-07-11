package com.sparta.team15.service;

import com.sparta.team15.dto.LoginRequestDto;
import com.sparta.team15.dto.SignUpRequestDto;
import com.sparta.team15.dto.SignUpResponseDto;
import com.sparta.team15.entity.User;
import com.sparta.team15.entity.UserRoleEnum;
import com.sparta.team15.entity.UserStatusEnum;
import com.sparta.team15.exception.DuplicatedException;
import com.sparta.team15.exception.MismatchException;
import com.sparta.team15.exception.NotFoundException;
import com.sparta.team15.exception.UserErrorCode;
import com.sparta.team15.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${auth.manager_token}")
  private String MANAGER_TOKEN;

  public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
    String username = requestDto.getUsername();
    String password = requestDto.getPassword();

    // 회원 중복 확인
    if (userRepository.findByUsername(username).isPresent()) {
      throw new DuplicatedException(UserErrorCode.DUPLICATED_USER);
    }

    // 사용자 ROLE 확인
    UserRoleEnum role = UserRoleEnum.USER;
    if(requestDto.isManager()) {
      if (!MANAGER_TOKEN.equals(requestDto.getManagerToken())){
        throw new MismatchException(UserErrorCode.NOT_MANAGER);
      }
      role = UserRoleEnum.ADMIN;
    }

    User user = new User(
        username,
        password,
        requestDto.getName(),
        role
    );
    userRepository.save(user);
    return new SignUpResponseDto(user);
  }


  @Transactional
  public void withDraw(Long id, String password) {
    User user = findById(id);
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new MismatchException(UserErrorCode.PW_MISMATCH);
    }
    if (user.getStatus().equals(UserStatusEnum.NON_USER)) {
      throw new NotFoundException(UserErrorCode.WITHDRAW_USER);
    }
    user.withDraw();
  }

  private User findById(Long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
    );
  }

  @Transactional
  public boolean logout(Long id) {
    User user = findById(id);
    return user.logout();
  }

  public void refreshTokenCheck(String username, String refreshToken) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
    );
    if (!user.getRefreshToken().equals(refreshToken)) {
      throw new MismatchException(UserErrorCode.REFRESH_TOKEN_MISMATCH);
    }
  }

}
