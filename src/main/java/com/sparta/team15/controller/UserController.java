package com.sparta.team15.controller;

import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.dto.SignUpRequestDto;
import com.sparta.team15.dto.WithDrawUserRequestDto;
import com.sparta.team15.entity.UserRoleEnum;
import com.sparta.team15.entity.UserStatusEnum;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.jwt.JwtTokenHelper;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenHelper jwtTokenHelper;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> signUp(
        @Valid @RequestBody SignUpRequestDto requestDto) {
        userService.signUp(requestDto);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.SIGNUP_SUCCESS));
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<ResponseMessageDto> withDraw(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @Valid @RequestBody WithDrawUserRequestDto requestdto) {
        userService.withDraw(userDetails.getUser().getId(),requestdto.getPassword());
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.WITHDRAW_SUCCESS_MESSAGE));
    }

    @PatchMapping("/logout")
    public ResponseEntity<ResponseMessageDto> logout(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.logout(userDetails.getUser().getId());
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.LOGOUT_SUCCESS));
    }

    // 토큰 재발급
    @GetMapping("/refresh")
    public ResponseEntity<ResponseMessageDto> refresh(
        @RequestHeader(JwtTokenHelper.AUTHORIZATION_HEADER) String accessToken,
        @RequestHeader(JwtTokenHelper.REFRESH_TOKEN_HEADER) String refreshToken) {

        Claims claims = jwtTokenHelper.getExpiredAccessToken(accessToken);
        String username = claims.getSubject();
        String status = claims.get("status").toString();
        String role = claims.get("auth").toString();

        UserStatusEnum statusEnum = UserStatusEnum.valueOf(status);
        UserRoleEnum roleEnum = UserRoleEnum.valueOf(role);

        userService.refreshTokenCheck(username, refreshToken);

        String newAccessToken = jwtTokenHelper.createToken(username, statusEnum, roleEnum);
        return ResponseEntity.ok()
            .header(JwtTokenHelper.AUTHORIZATION_HEADER, newAccessToken)
            .body(new ResponseMessageDto(MessageEnum.UPDATE_TOKEN_SUCCESS_MESSAGE,newAccessToken));
    }

}
