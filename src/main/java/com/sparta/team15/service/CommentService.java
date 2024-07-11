package com.sparta.team15.service;

import com.sparta.team15.dto.CommentRequestDto;
import com.sparta.team15.entity.Comment;
import com.sparta.team15.entity.User;
import com.sparta.team15.entity.Card;
import com.sparta.team15.repository.CommentRepository;
import com.sparta.team15.repository.UserRepository;
import com.sparta.team15.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          CardRepository cardRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(commentRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Card card = cardRepository.findById(commentRequestDto.getCardId())
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));

        Comment comment = new Comment(user, card, commentRequestDto.getContent());
        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}