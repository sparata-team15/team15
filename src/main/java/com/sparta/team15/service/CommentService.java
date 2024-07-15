package com.sparta.team15.service;

import com.sparta.team15.dto.CommentRequestDto;
import com.sparta.team15.dto.CommentResponseDto;
import com.sparta.team15.entity.Card;
import com.sparta.team15.entity.Comment;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.repository.CardRepository;
import com.sparta.team15.repository.CommentRepository;
import com.sparta.team15.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;

    public Page<CommentResponseDto> getAllComments(Long cardId, int page) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(
                () -> new IllegalArgumentException(MessageEnum.INVALID_CARD_ID.getMessage()));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Comment> commentsPage = commentRepository.findAllByCard(card, pageable);

        return commentsPage.map(CommentResponseDto::new);
    }

    @Transactional
    public void createComment(UserDetailsImpl userDetails, Long cardId, CommentRequestDto requestDto) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(
                () -> new IllegalArgumentException(MessageEnum.INVALID_CARD_ID.getMessage()));

        Comment comment = new Comment(userDetails.getUser(), card, requestDto.getContent());
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(UserDetailsImpl userDetails, Long commentId, CommentRequestDto requestDto) {
        if (!commentRepository.existsByIdAndCardCardId(commentId, requestDto.getCardId())) {
            throw new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage());
        }

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(
                () -> new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage()));

        if (comment.getUser().getId() != userDetails.getUser().getId()) {
            throw new IllegalStateException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        comment.update(requestDto.getContent());
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(UserDetailsImpl userDetails, Long commentId, Long cardId) {
        if (!commentRepository.existsByIdAndCardCardId(commentId, cardId)) {
            throw new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage());
        }

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(
                () -> new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage()));

        if (comment.getUser().getId() != userDetails.getUser().getId()) {
            throw new IllegalStateException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        commentRepository.delete(comment);
    }
}