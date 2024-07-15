package com.sparta.team15.service;

import com.sparta.team15.dto.BoardColumnOrderRequestDto;
import com.sparta.team15.dto.BoardColumnRequestDto;
import com.sparta.team15.dto.BoardColumnResponseDto;
import com.sparta.team15.entity.*;
import com.sparta.team15.exception.BoardColumnErrorCode;
import com.sparta.team15.exception.DuplicatedException;
import com.sparta.team15.exception.NotFoundException;
import com.sparta.team15.exception.UserErrorCode;
import com.sparta.team15.repository.BoardColumnRepository;
import com.sparta.team15.repository.BoardRepository;
import com.sparta.team15.repository.BoardUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardColumnService {

    private final BoardRepository boardRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final BoardUserRepository boardUserRepository;


    /**
     * 컬럼 생성
     * @param requestDto
     * @param loginUser
     * @return BoardColumnResponseDto
     */
    public BoardColumnResponseDto addBoardColumn(BoardColumnRequestDto requestDto, User loginUser) {

        // 매니저 인지 확인
        if(!loginUser.getRole().equals(UserRoleEnum.MANAGER)){
            throw new NotFoundException(BoardColumnErrorCode.NO_AUTHENTICATION);
        };

        // 보드 존재 확인
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                () -> new NotFoundException(UserErrorCode.NOT_FOUND_BOARD)
        );

        // 이미 존재하는 상태 이름인지 확인 후 예외처리

        if(boardColumnRepository.existsBoardColumnByTitleAndBoardId(requestDto.getTitle(), requestDto.getBoardId())){
            throw new DuplicatedException(BoardColumnErrorCode.DUPLICATED_COLUMN_NAME);
        };

        BoardColumn boardColumn = new BoardColumn(requestDto, board);

        BoardColumn savedBoardColumn = boardColumnRepository.save(boardColumn);

        return new BoardColumnResponseDto(savedBoardColumn);
    }

    /**
     * 컬럼 삭제
     * @param columnId
     * @param loginUser
     */
    @Transactional
    public void deleteBoardColumn(Long columnId, User loginUser){
        // 컬럼 확인
        BoardColumn boardColumn = boardColumnRepository.findById(columnId).orElseThrow(
                () -> new NotFoundException(BoardColumnErrorCode.NOT_FOUND_COLUMN));

        // 보드 존재 확인
        boardRepository.findById(boardColumn.getBoard().getId()).orElseThrow(
                () -> new NotFoundException(UserErrorCode.NOT_FOUND_BOARD)
        );

        // 팀에 속해있는지 확인
        boardUserRepository.findByUserIdAndBoardId(loginUser.getId(), boardColumn.getBoard().getId()).orElseThrow(
                () -> new NotFoundException(BoardColumnErrorCode.NOT_TEAM_MEMBER)
        );

        // 매니저 인지 확인
        if(!loginUser.getRole().equals(UserRoleEnum.MANAGER)){
            throw new NotFoundException(BoardColumnErrorCode.NO_AUTHENTICATION);
        };

        boardColumnRepository.delete(boardColumn);
    }

    /**
     * 컬럼 순서 이동
     * @param columnId
     * @param requestDto
     * @param loginUser
     * @return BoardColumnResponseDto
     */
    @Transactional
    public BoardColumnResponseDto updateBoardColumnOrder(Long columnId, BoardColumnOrderRequestDto requestDto, User loginUser){
        // 컬럼 확인
        BoardColumn boardColumn = boardColumnRepository.findById(columnId).orElseThrow(
                () -> new NotFoundException(BoardColumnErrorCode.NOT_FOUND_COLUMN));

        // 보드 존재 확인
        boardRepository.findById(boardColumn.getBoard().getId()).orElseThrow(
                () -> new NotFoundException(UserErrorCode.NOT_FOUND_BOARD)
        );

        // 팀에 속해있는지 확인
        boardUserRepository.findByUserIdAndBoardId(loginUser.getId(), boardColumn.getBoard().getId()).orElseThrow(
                () -> new NotFoundException(BoardColumnErrorCode.NOT_TEAM_MEMBER)
        );

        // 매니저 인지 확인
        if(!loginUser.getRole().equals(UserRoleEnum.MANAGER)){
            throw new NotFoundException(BoardColumnErrorCode.NO_AUTHENTICATION);
        };

        boardColumn.updatePosition(requestDto.getPosition());

        return new BoardColumnResponseDto(boardColumn);
    }
}
