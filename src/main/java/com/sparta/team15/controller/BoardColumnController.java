package com.sparta.team15.controller;

import com.sparta.team15.dto.BoardColumnRequestDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.entity.User;
import com.sparta.team15.enums.MessageEnum;
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
    public ResponseEntity<ResponseMessageDto> addBoardColumn(@RequestBody @Valid BoardColumnRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User loginUser = new User();
        boardColumnService.addBoardColumn(requestDto, loginUser);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COLUMN_CREATED));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoardColumn(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User loginUser = new User();
        boardColumnService.deleteBoardColumn(boardId, loginUser);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COLUMN_DELETED));
    }

    @PatchMapping("/order")
    public ResponseEntity<?> updateBoardColumnPosition(){

        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COLUMN_UPDATE_POSITION));
    }
}
