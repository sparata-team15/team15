package com.sparta.team15.dto;

import com.sparta.team15.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private long id;
    private String title;
    private String description;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
    }

}
