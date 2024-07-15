package com.sparta.team15.dto;

import com.sparta.team15.entity.BoardColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardColumnResponseDto {
    private Long boardId;
    private Long columnId;
    private String title;
    private Double position;

    public BoardColumnResponseDto(BoardColumn savedBoardColumn) {
        this.boardId = savedBoardColumn.getBoard().getId();
        this.columnId = savedBoardColumn.getId();
        this.title = savedBoardColumn.getTitle();
        this.position = savedBoardColumn.getPosition();
    }
}
