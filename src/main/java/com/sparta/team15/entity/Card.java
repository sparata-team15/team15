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

//    private Long userId;
    private String author;

    private Long columnId;

    private String content;

    private String description;

    private Date date;

    public Card(String author, long columnId, String content, String description, Date date) {
        this.author = author;
        this.columnId = columnId;
        this.content = content;
        this.description = description;
        this.date = date;
    }

    public void update(String author, String content, String description, Date date) {
        this.author = author;
        this.content = content;
        this.description = description;
        this.date = date;
    }
}

