package com.sparta.team15.repository;

import com.sparta.team15.entity.BoardUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {

    boolean existsByUserIdAndBoardId(Long userId, Long boardId);

    List<BoardUser> findAllByBoardIdAndIsDeletedIsFalse(Long boardId);
}
