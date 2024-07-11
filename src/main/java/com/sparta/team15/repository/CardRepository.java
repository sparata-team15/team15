package com.sparta.team15.repository;

import com.sparta.team15.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByUserId(Long userId, Pageable pageable);

    List<Card> findByColumnId(Long columnId, Pageable pageable);
}
