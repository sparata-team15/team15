package com.sparta.team15.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Card extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id", nullable = false)
    private BoardColumn boardColumn;

    private String author;

    private String content;

    private String description;

    private Date date;

    private int position;

    private String createdAt;

    private String modifiedAt;

    public Card(User user, String author, BoardColumn boardColumn, String content, String description, Date date) {
        this.user = user;
        this.author = author;
        this.boardColumn = boardColumn;
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

    public void updatePosition(int position) {
        this.position = position;
    }
}

