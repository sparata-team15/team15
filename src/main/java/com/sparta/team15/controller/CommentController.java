package com.sparta.team15.controller;

import com.sparta.team15.dto.CommentRequestDto;
import com.sparta.team15.dto.CommentResponseDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/{boardId}/columns/{columnId}/cards/{cardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cardId,
            @RequestBody @Valid CommentRequestDto requestDto) {
        commentService.createComment(userDetails, cardId, requestDto);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COMMENT_CREATED));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@PathVariable Long cardId) {
        List<CommentResponseDto> comments = commentService.getAllComments(cardId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseMessageDto> updateComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long commentId,
            @RequestBody @Valid CommentRequestDto requestDto) {
        commentService.updateComment(userDetails, commentId, requestDto);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COMMENT_UPDATED));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseMessageDto> deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long commentId) {
        commentService.deleteComment(userDetails, commentId);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.COMMENT_DELETED));
    }
}