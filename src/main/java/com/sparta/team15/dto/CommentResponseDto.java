package com.sparta.team15.dto;

import com.sparta.team15.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private Long cardId;
    private String content;
    private String author;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.cardId = comment.getCard().getCardId();
        this.content = comment.getContent();
        this.author = comment.getUser().getUsername();
    }
}