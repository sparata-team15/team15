package com.sparta.team15.repository;

import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.BoardUser;
import com.sparta.team15.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {

    List<BoardUser> findAllByBoardIdAndIsDeletedIsFalse(Long boardId);

    List<BoardUser> findAllByUser_Id(Long userId);

    Optional<BoardUser> findByUserIdAndBoardId(Long userId, Long boardId);

    boolean existsByUserAndBoard(User user, Board board);
}
