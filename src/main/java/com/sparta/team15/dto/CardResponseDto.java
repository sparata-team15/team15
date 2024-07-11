package com.sparta.team15.dto;

import com.sparta.team15.entity.Card;
import lombok.Getter;

@Getter
public class CardResponseDto {
    private Long userId;
    private Long columnId;
    private String content;
    private String description;

    public CardResponseDto(Card card) {
        this.userId = card.getUserId();
        this.columnId = card.getColumnId();
        this.content = card.getContent();
        this.description = card.getDescription();
    }
}
