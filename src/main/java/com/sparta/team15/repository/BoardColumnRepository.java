package com.sparta.team15.repository;

import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.BoardColumn;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
    boolean existsBoardColumnByTitleAndBoardId(String title, Long boardId);

    List<BoardColumn> findAllByBoardId(Long boardId);
}
