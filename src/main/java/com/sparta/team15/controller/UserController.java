//package com.sparta.team15.controller;
//
//import com.sparta.team15.dto.LoginRequestDto;
//import com.sparta.team15.dto.ResponseMessageDto;
//import com.sparta.team15.dto.SignUpRequestDto;
////import com.sparta.team15.dto.WithDrawUserRequestDto;
//import com.sparta.team15.enums.MessageEnum;
//import com.sparta.team15.service.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/users")
//@RequiredArgsConstructor
//public class UserController {
//  private UserService userService;
//
//  @PostMapping
//  public ResponseEntity<ResponseMessageDto> signUp (@Valid @RequestBody SignUpRequestDto requestDto) {
//      userService.signUp(requestDto);
//      return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.SIGNUP_SUCCESS));
//  }
//
//  @PutMapping("/withDraw/{id}")
//  public ResponseEntity<ResponseMessageDto> withDraw(@PathVariable Long id, String password) {
//    userService.withDraw(id, password);
//    return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.WITHDRAW_SUCCESS_MESSAGE));
//  }
//
//  @PostMapping("/login")
//  public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto) {
//    userService.login(requestDto);
//    return ResponseEntity.ok().body("로그인 성공");
//  }
//
//  @PutMapping("/logout/{id}")
//  public ResponseEntity<ResponseMessageDto> logout(@PathVariable Long id) {
//    userService.logout(id);
//    return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.LOGOUT_SUCCESS));
//  }
//
//
//
//
//
//
//
//
//
//
//
//}
