package com.sparta.team15.dto;

import com.sparta.team15.entity.Card;
import lombok.Getter;

@Getter
public class CardResponseDto {

    private String author;
    private Long columnId;
    private String content;
    private String description;

    public CardResponseDto(Card card) {
        this.author = card.getAuthor();
        this.columnId = card.getBoardColumn().getId();
        this.content = card.getContent();
        this.description = card.getDescription();
    }
}
