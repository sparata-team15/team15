package com.sparta.team15.service;

import com.sparta.team15.dto.CommentRequestDto;
import com.sparta.team15.dto.CommentResponseDto;
import com.sparta.team15.entity.Card;
import com.sparta.team15.entity.Comment;
import com.sparta.team15.entity.User;
import com.sparta.team15.entity.Card;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.repository.CommentRepository;
import com.sparta.team15.repository.UserRepository;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    public void createComment(UserDetailsImpl userDetails, Long cardId, CommentRequestDto requestDto) {
        User user = userRepository.findById(userDetails.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_USER_ID.getMessage()));
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_CARD_ID.getMessage()));

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          CardRepository cardRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        Comment comment = new Comment(user, card, requestDto.getContent());
        commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
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

    public Comment saveComment(CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(commentRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Card card = cardRepository.findById(commentRequestDto.getCardId())
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));
    // 댓글 수정
    public void updateComment(UserDetailsImpl userDetails, Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage()));

        if (!Long.valueOf(comment.getUser().getId()).equals(userDetails.getUser().getId())) {
            throw new IllegalStateException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        Comment comment = new Comment(user, card, commentRequestDto.getContent());
        return commentRepository.save(comment);
        comment.update(requestDto.getContent());
        commentRepository.save(comment);
    }

    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        } else {
            return false;
    // 댓글 삭제
    public void deleteComment(UserDetailsImpl userDetails, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_COMMENT_ID.getMessage()));

        if (!Long.valueOf(comment.getUser().getId()).equals(userDetails.getUser().getId())) {
            throw new IllegalStateException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        commentRepository.delete(comment);
    }
}