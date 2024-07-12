package com.sparta.team15.repository;

import com.sparta.team15.entity.BoardColumn;
import com.sparta.team15.entity.Card;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByAuthor(String author, Pageable pageable);

    List<Card> findByBoardColumn(BoardColumn boardColumn, Pageable pageable);
}
