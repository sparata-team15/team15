package com.sparta.team15.service;

import com.sparta.team15.dto.BoardColumnRequestDto;
import com.sparta.team15.entity.Board;
import com.sparta.team15.entity.BoardColumn;
import com.sparta.team15.entity.User;
import com.sparta.team15.repository.BoardColumnRepository;
import com.sparta.team15.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardColumnService {

    private final BoardRepository boardRepository;
    private final BoardColumnRepository boardColumnRepository;


    /**
     * 컬럼 생성
     * @param requestDto
     * @param loginUser
     */
    public void addBoardColumn(BoardColumnRequestDto requestDto, User loginUser) {
        // todo: 매니저 권한 확인 후 예외처리

        // todo: 예외처리
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 Board입니다. ")
        );

        // 이미 존재하는 상태 이름인지 확인 후 예외처리
        Optional<BoardColumn> boardColumnByTitle = getBoardColumnByTitle(requestDto.getTitle());
        if(boardColumnByTitle.isPresent()){
            // todo: 예외처리
            throw new IllegalArgumentException("이미 존재하는 컬럼 이름입니다.");
        }

        BoardColumn boardColumn = new BoardColumn(requestDto, board);

        boardColumnRepository.save(boardColumn);
    }

    /**
     * 컬럼 삭제
     */
    public void deleteBoardColumn(){
        // todo: 팀의 매니저인지 확인
        // todo: 매니저 권한 확인
        // todo: board 존재 확인

    }

    /**
     * 컬럼 순서 이동
     */
    public void updateBoardColumnOrder(){

    }

    private Optional<BoardColumn> getBoardColumnByTitle(String title){
        return boardColumnRepository.findByTitle(title);
    }
}
