package com.sparta.team15.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageResponse<T> {

    private Page<BoardResponseDto> content;

    public PageResponse(Page<BoardResponseDto> content) {
        this.content = content;
    }
}
