package com.sparta.team15.controller;


import com.sparta.team15.dto.BoardColumnRequestDto;
import com.sparta.team15.entity.User;
import com.sparta.team15.security.UserDetailImpl;
import com.sparta.team15.service.BoardColumnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/columns")
public class BoardColumnController {

    private final BoardColumnService boardColumnService;

    @PostMapping
    public ResponseEntity<?> addBoardColumn(@RequestBody @Valid BoardColumnRequestDto requestDto, @AuthenticationPrincipal UserDetailImpl userDetail){
        User loginUser = new User(1L, "user1");
        boardColumnService.addBoardColumn(requestDto, loginUser);
        return ResponseEntity.ok().body("컬럼 생성 성공");
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoardColumn(@PathVariable Long boardId){

        return ResponseEntity.ok().body("컬럼 삭제 성공");
    }

    @PatchMapping("/order")
    public ResponseEntity<?> updateBoardColumnPosition(){

        return ResponseEntity.ok().body("포지션 변경 성공");
    }
}
