package com.sparta.team15.repository;

import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByCreatedByAndIsDeletedFalse(User createdBy);
    Optional<Board> findByIdAndIsDeletedFalse(Long id);

    List<Board> findAllByIdInAndIsDeletedFalse(List<Long> boardIds);

    Page<Board> findAllByIdInAndIsDeletedFalse(List<Long> boardIds, Pageable pageable);

    boolean existsByIdAndIsDeletedFalse(Long boardId);
}
