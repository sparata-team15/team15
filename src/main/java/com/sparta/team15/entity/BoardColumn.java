package com.sparta.team15.entity;

import com.sparta.team15.dto.BoardColumnRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double position;

    public BoardColumn(BoardColumnRequestDto requestDto, Board board) {
        this.board = board;
        this.title = requestDto.getTitle();
        this.position = requestDto.getPosition();
    }

    public void updatePosition(Double position) {
        this.position = position;
    }
}
