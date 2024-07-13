package com.sparta.team15.repository;

import com.sparta.team15.entity.Card;
import com.sparta.team15.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCard(Card card);
}