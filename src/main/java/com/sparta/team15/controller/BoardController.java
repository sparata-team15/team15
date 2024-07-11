package com.sparta.team15.controller;

import com.sparta.team15.dto.BoardRequestDto;
import com.sparta.team15.dto.BoardResponseDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.service.BoardService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 보드 생성
     * @param userDetails
     * @param requestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody @Valid BoardRequestDto requestDto) {
        boardService.createBoard(requestDto, userDetails.getUser());
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.BOARDS_CREATE_SUCCESS));
    }

    /**
     * 보드 수정
     * @param userDetails
     * @param requestDto
     * @param boardId
     * @return
     */
    @PatchMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody @Valid BoardRequestDto requestDto,
        @PathVariable Long boardId) {
        boardService.updateBoard(boardId, requestDto,
            userDetails.getUser());
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.BOARDS_UPDATE_SUCCESS));
    }

    /**
     * 보드 삭제
     * @param userDetails
     * @param boardId
     * @return
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long boardId) {
        boardService.deleteBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.BOARDS_DELETE_SUCCESS));
    }

    /**
     * 모든 보드 조회
     * @param userDetails
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getBoards(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<BoardResponseDto> responseDtoList = boardService.getBoards(userDetails.getUser());
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.BOARDS_READ_SUCCESS, responseDtoList));
    }

    /**
     * 단일 보드 조회
     * @param userDetails
     * @param boardId
     * @return
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long boardId)   {
        BoardResponseDto responseDto = boardService.getBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.BOARDS_READ_SUCCESS));
    }

    /**
     * 보드 유저 초대
     * @param userDetails
     * @param boardId
     * @return
     */
    @PostMapping("/{boardId}")
    public ResponseEntity<?> inviteUser(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long boardId) {
        boardService.inviteUser(userDetails.getUser(), boardId);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.BOARDS_INVITE_SUCCESS));
    }


}
