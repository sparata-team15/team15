package com.sparta.team15.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.sparta.team15.dto.CommentRequestDto;
import com.sparta.team15.dto.CommentResponseDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping
    public ResponseEntity<ResponseMessageDto> createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid CommentRequestDto requestDto) {
        commentService.createComment(userDetails, requestDto.getCardId(), requestDto);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COMMENT_CREATED));
    }

    // 댓글 조회
    @GetMapping
    public ResponseEntity<Page<CommentResponseDto>> getAllComments(
            @RequestParam Long cardId,
            @RequestParam int page) {
        Page<CommentResponseDto> comments = commentService.getAllComments(cardId, page);
        return ResponseEntity.ok(comments);
    }

    //댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseMessageDto> updateComment(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long commentId,
        @RequestBody @Valid CommentRequestDto requestDto) {
        commentService.updateComment(userDetails, commentId, requestDto);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COMMENT_UPDATED));
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseMessageDto> deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long commentId,
            @RequestParam Long cardId) {
        commentService.deleteComment(userDetails, commentId, cardId);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COMMENT_DELETED));
    }
}