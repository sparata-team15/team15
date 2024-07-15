package com.sparta.team15.repository;

import com.sparta.team15.entity.Card;
import com.sparta.team15.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByCard(Card card, Pageable pageable);
    boolean existsByIdAndCardId(Long commentId, Long cardId);
}