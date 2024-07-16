package com.sparta.team15.controller;

import com.sparta.team15.dto.BoardColumnOrderRequestDto;
import com.sparta.team15.dto.BoardColumnRequestDto;
import com.sparta.team15.dto.BoardColumnResponseDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.entity.User;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.repository.UserRepository;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.service.BoardColumnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/columns")
public class BoardColumnController {

    private final BoardColumnService boardColumnService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> addBoardColumn(
        @RequestBody @Valid BoardColumnRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User loginUser = userDetails.getUser();
        BoardColumnResponseDto boardColumnResponseDto = boardColumnService.addBoardColumn(
            requestDto, loginUser);

        return ResponseEntity.ok(
            new ResponseMessageDto(MessageEnum.COLUMN_CREATED, boardColumnResponseDto));
    }

    @DeleteMapping("/{columnId}")
    public ResponseEntity<ResponseMessageDto> deleteBoardColumn(
        @PathVariable Long columnId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User loginUser = userDetails.getUser();
        boardColumnService.deleteBoardColumn(columnId, loginUser);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COLUMN_DELETED));
    }

    @PatchMapping("/updatePositions")
    public ResponseEntity<ResponseMessageDto> updateBoardColumnPosition(
        @RequestBody List<BoardColumnOrderRequestDto> requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

//        User loginUser = userRepository.findByUsername("user1").get();
        User loginUser = userDetails.getUser();
        BoardColumnResponseDto boardColumnResponseDto = boardColumnService.updateBoardColumnOrder(requestDto, loginUser);

        return ResponseEntity.ok(
            new ResponseMessageDto(MessageEnum.COLUMN_DELETED, boardColumnResponseDto));
    }

    @GetMapping("/boards/{boardId}")
    public List<BoardColumnResponseDto> getBoardColumnList(@PathVariable Long boardId){
        return boardColumnService.getBoardColumnList(boardId);
    }
}
