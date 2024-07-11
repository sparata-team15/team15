package com.sparta.team15.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private Long userId;

    private Long columnId;

    private String content;

    private String description;

    private Date date;

    public Card(long userId, long columnId, String content, String description, Date date) {
        this.userId = userId;
        this.columnId = columnId;
        this.content = content;
        this.description = description;
        this.date = date;
    }

    public void update(Long userId, String content, String description, Date date) {
        this.userId = userId;
        this.content = content;
        this.description = description;
        this.date = date;
    }
}

