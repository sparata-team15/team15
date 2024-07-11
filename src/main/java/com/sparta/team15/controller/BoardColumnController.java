package com.sparta.team15.controller;


import com.sparta.team15.dto.BoardColumnRequestDto;
import com.sparta.team15.entity.User;
import com.sparta.team15.security.UserDetailsImpl;
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
    public ResponseEntity<?> addBoardColumn(@RequestBody @Valid BoardColumnRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User loginUser = new User();
        boardColumnService.addBoardColumn(requestDto, loginUser);
        //todo: message 변경
        return ResponseEntity.ok().body("컬럼 생성 성공");
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoardColumn(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User loginUser = new User();
        boardColumnService.deleteBoardColumn(boardId, loginUser);
        return ResponseEntity.ok().body("컬럼 삭제 성공");
    }

    @PatchMapping("/order")
    public ResponseEntity<?> updateBoardColumnPosition(){

        return ResponseEntity.ok().body("포지션 변경 성공");
    }
}
