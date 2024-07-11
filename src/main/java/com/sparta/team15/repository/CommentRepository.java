package com.sparta.team15.repository;

import com.sparta.team15.entity.Comment;
import com.sparta.team15.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCard(Card card);
}