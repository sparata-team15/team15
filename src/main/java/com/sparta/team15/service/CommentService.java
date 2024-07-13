package com.sparta.team15.service;

import com.sparta.team15.dto.CommentRequestDto;
import com.sparta.team15.dto.CommentResponseDto;
import com.sparta.team15.entity.Card;
import com.sparta.team15.entity.Comment;
import com.sparta.team15.entity.User;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.repository.CardRepository;
import com.sparta.team15.repository.CommentRepository;
import com.sparta.team15.repository.UserRepository;
import com.sparta.team15.security.UserDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    @Transactional
    public void createComment(UserDetailsImpl userDetails, Long cardId, CommentRequestDto requestDto) {
        User user = userRepository.findById(userDetails.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_USER_ID.getMessage()));
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_CARD_ID.getMessage()));

        Comment comment = new Comment(user, card, requestDto.getContent());
        commentRepository.save(comment);
    }

    // 댓글 전체 목록 조회
    public List<CommentResponseDto> getAllComments(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_CARD_ID.getMessage()));

        List<Comment> comments = commentRepository.findAllByCard(card);
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDto.add(new CommentResponseDto(comment));
        }
        return commentResponseDto;
    }

    // 댓글 수정
    @Transactional
    public void updateComment(UserDetailsImpl userDetails, Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage()));

        if (comment.getUser().getId() != userDetails.getUser().getId()) {
            throw new IllegalStateException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        comment.update(requestDto.getContent());
        commentRepository.save(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(UserDetailsImpl userDetails, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage()));

        if (comment.getUser().getId() != userDetails.getUser().getId()) {
            throw new IllegalStateException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        commentRepository.delete(comment);
    }
}