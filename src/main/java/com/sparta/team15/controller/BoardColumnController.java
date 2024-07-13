package com.sparta.team15.controller;

import com.sparta.team15.dto.BoardColumnOrderRequestDto;
import com.sparta.team15.dto.BoardColumnRequestDto;
import com.sparta.team15.dto.BoardColumnResponseDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.entity.User;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.service.BoardColumnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/columns")
public class BoardColumnController {

    private final BoardColumnService boardColumnService;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> addBoardColumn(
            @RequestBody @Valid BoardColumnRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        User loginUser = userDetails.getUser();
        BoardColumnResponseDto boardColumnResponseDto = boardColumnService.addBoardColumn(requestDto, loginUser);

        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COLUMN_CREATED, boardColumnResponseDto));
    }

    @DeleteMapping("/{columnId}")
    public ResponseEntity<ResponseMessageDto> deleteBoardColumn(
            @PathVariable Long columnId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        User loginUser = userDetails.getUser();
        boardColumnService.deleteBoardColumn(columnId, loginUser);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COLUMN_DELETED));
    }

    @PatchMapping("/{columnId}/order")
    public ResponseEntity<ResponseMessageDto> updateBoardColumnPosition(
            @PathVariable Long columnId,
            @RequestBody @Valid BoardColumnOrderRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        User loginUser = userDetails.getUser();
        BoardColumnResponseDto boardColumnResponseDto = boardColumnService.updateBoardColumnOrder(columnId, requestDto, loginUser);

        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COLUMN_DELETED, boardColumnResponseDto));
    }
}
