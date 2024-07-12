package com.sparta.team15.service;

import com.sparta.team15.dto.BoardInviteRequestDto;
import com.sparta.team15.dto.BoardRequestDto;
import com.sparta.team15.dto.BoardResponseDto;
import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.User;
import com.sparta.team15.entity.UserRoleEnum;
import com.sparta.team15.exception.AuthorizedException;
import com.sparta.team15.exception.DuplicatedException;
import com.sparta.team15.exception.NotFoundException;
import com.sparta.team15.exception.UserErrorCode;
import com.sparta.team15.repository.BoardRepository;
import com.sparta.team15.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardUserService boardUserService;

    /**
     * 보드 생성
     *
     * @param boardRequestDto
     * @param user
     * @return
     */
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Optional<User> createdBy = userRepository.findById(user.getId());
        if (createdBy.get().getRole().equals(UserRoleEnum.USER)) {
            throw new AuthorizedException(UserErrorCode.NOT_ACCEPTABLE_TO_MAKE_BOARD);
        }
        Board board = boardRepository.save(boardRequestDto.toEntity(createdBy.get()));
        boardUserService.saveUserToBoard(board, createdBy.get());
        return new BoardResponseDto(board);
    }

    /**
     * 보드 수정
     *
     * @param boardId
     * @param requestDto
     * @param user
     * @return
     */
    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto requestDto, User user) {
        Board board = getBoardAndAuth(user, boardId);
        if (requestDto.getTitle() == null) {
            throw new NotFoundException(UserErrorCode.INVALID_REQUEST);
        }
        if (user.getRole().equals(UserRoleEnum.USER)) {
            throw new AuthorizedException(UserErrorCode.NOT_AUTHORIZATION_ABOUT_BOARD);
        }
        board.update(requestDto);
        return new BoardResponseDto(board);
    }

    /**
     * 보드 삭제
     * @param boardId
     * @param user
     */
    @Transactional
    public void deleteBoard(Long boardId, User user) {
        Board board = getBoardAndAuth(user, boardId);

        if (user.getRole().equals(UserRoleEnum.USER)) {
            throw new AuthorizedException(UserErrorCode.NOT_AUTHORIZATION_ABOUT_BOARD);
        }

        if (board.getIsDeleted()) {
            throw new NotFoundException(UserErrorCode.NOT_FOUND_BOARD);
        }

        board.deleteBoard();
        boardUserService.deleteBoardUsers(board);
    }

    /**
     * 모든 보드 조회
     * @param user
     * @return
     */
    public List<BoardResponseDto> getBoards(User user) {
        List<Long> boardIds = boardUserService.getBoardIdsByUserId(user.getId());

        List<Board> boards = boardRepository.findAllByIdInAndIsDeletedFalse(boardIds);

        List<BoardResponseDto> boardResponseDtos = boards.stream()
            .map(BoardResponseDto::new)
            .collect(Collectors.toList());

        return boardResponseDtos;
    }

    /**
     * 단일 보드 조회
     * @param boardId
     * @param user
     * @return
     */
    public BoardResponseDto getBoard(Long boardId, User user) {
        Board board = getBoardAndAuth(user, boardId);
        return new BoardResponseDto(board);
    }

    /**
     * 보드 유저 초대
     *
     * @param user
     * @param boardId
     */
    public void inviteUser(User user, Long boardId, BoardInviteRequestDto requestDto) {
        Board board = getBoardAndAuth(user, boardId);

        Optional<User> invitedUserOptional = userRepository.findById(requestDto.getUserId());
        if (invitedUserOptional.isEmpty()) {
            throw new NotFoundException(UserErrorCode.USER_NOT_FOUND);
        }

        User invitedUser = invitedUserOptional.get();

        if (boardUserService.isExistedUser(Optional.of(invitedUser), board)) {
            throw new DuplicatedException(UserErrorCode.ALREADY_INVITED_USER);
        }

        boardUserService.saveUserToBoard(board, invitedUser);
    }

    /**
     * 보드와 권한 확인
     *
     * @param user
     * @param boardId
     * @return
     */
    private Board getBoardAndAuth(User user, Long boardId) {
        Board board = boardRepository.findByIdAndIsDeletedFalse(boardId)
            .orElseThrow(() -> new NotFoundException(UserErrorCode.NOT_FOUND_BOARD));

        Optional<User> isUser = userRepository.findById(user.getId());

        if (!boardUserService.isExistedUser(isUser, board)) {
            throw new NotFoundException(UserErrorCode.NOT_FOUND_BOARD);
        }

        return board;
    }


}
