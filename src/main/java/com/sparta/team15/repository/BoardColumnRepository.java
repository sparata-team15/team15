package com.sparta.team15.repository;

import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
    Optional<BoardColumn> findByTitleAndBoardId(String title, Long boardId);


}
