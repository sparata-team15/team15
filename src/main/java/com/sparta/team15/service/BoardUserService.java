package com.sparta.team15.service;

import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.BoardUser;
import com.sparta.team15.entity.User;
import com.sparta.team15.repository.BoardUserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardUserService {

    private final BoardUserRepository boardUserRepository;

    /**
     * 이미 초대한 유저인지 확인
     *
     * @param user
     * @param board
     * @return
     */
    public boolean isExistedUser(Optional<User> user, Board board) {
        return boardUserRepository.existsByUserIdAndBoardId(user.get().getId(), board.getId());
    }

    /**
     * 보드에 유저 초대
     *
     * @param board
     * @param user
     */
    @Transactional
    public void saveUserToBoard(Board board, User user) {
        BoardUser boardUser = BoardUser.builder().user(user).board(board).build();
        boardUserRepository.save(boardUser);
    }

    /**
     * 보드가 삭제될 때 유저도 삭제되게 함
     *
     * @param board
     */
    public void deleteBoardUsers(Board board) {
        List<BoardUser> boardUsers = boardUserRepository.findAllByBoardIdAndIsDeletedIsFalse(
            board.getId());
        for (BoardUser boardUser : boardUsers) {
            boardUser.delete();
        }
    }

    public List<Long> getBoardIdsByUserId(Long userId) {
        List<BoardUser> boardUsers = boardUserRepository.findAllByUser_Id(userId);

        // Extract boardIds from BoardUser entities
        List<Long> boardIds = boardUsers.stream()
            .map(BoardUser::getBoard)
            .map(Board::getId)
            .collect(Collectors.toList());

        return boardIds;
    }
}
