package com.sparta.team15.service;

import com.sparta.team15.dto.CardRequestDto;
import com.sparta.team15.dto.CardResponseDto;
import com.sparta.team15.entity.BoardColumn;
import com.sparta.team15.entity.Card;
import com.sparta.team15.entity.User;
import com.sparta.team15.exception.AuthorizedException;
import com.sparta.team15.exception.BoardColumnErrorCode;
import com.sparta.team15.exception.NotFoundException;
import com.sparta.team15.exception.UserErrorCode;
import com.sparta.team15.repository.BoardColumnRepository;
import com.sparta.team15.repository.BoardUserRepository;
import com.sparta.team15.repository.CardRepository;
import com.sparta.team15.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final BoardUserRepository boardUserRepository;

    // 카드 생성
    public void createCard(CardRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        BoardColumn boardColumn = findBoardColumn(requestDto.getColumnId());

        //쿼리 최적화 추가
        boolean isExistBoardUser = boardUserRepository
                .existsByUserAndBoard(user, boardColumn.getBoard());
        if (!isExistBoardUser) {
            throw new NotFoundException(BoardColumnErrorCode.NOT_TEAM_MEMBER);
        }

        Card card = new Card(
            user,
            requestDto.getAuthor(),
            boardColumn,
            requestDto.getContent(),
            requestDto.getDescription(),
            requestDto.getDate()
        );
        cardRepository.save(card);
    }

    // 카드 전체 목록 조회
    public List<CardResponseDto> getAllCards(int page, int size, UserDetailsImpl userDetails) {

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findAll(pageable).stream()
            .map(CardResponseDto::new).toList();
    }

    // 카드 상태별 조회
    public List<CardResponseDto> getCardsByStatus(Long columnId, int page, int size, UserDetailsImpl userDetails) {
        BoardColumn boardColumn = findBoardColumn(columnId);

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findByBoardColumn(boardColumn, pageable).stream()
            .map(CardResponseDto::new).toList();
    }

    // 카드 작업자별 조회
    public List<CardResponseDto> getCardsByAuthor(String author, int page, int size, UserDetailsImpl userDetails) {

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findByAuthor(author, pageable).stream()
            .map(CardResponseDto::new).toList();
    }

    // 카드 수정
    public void updateCard(Long cardId, CardRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Card card = findCard(cardId);


        if (card.getUser().getId() != user.getId()) {
            throw new AuthorizedException(UserErrorCode.NOT_AUTHORIZATION_ABOUT_CARD);
        }

        card.update(requestDto.getAuthor(), requestDto.getContent(), requestDto.getDescription(),
            requestDto.getDate());
        cardRepository.save(card);

    }

    // 카드 삭제
    public void deleteCard(Long cardId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Card card = findCard(cardId);

        if (card.getUser().getId() != user.getId()) {
            throw new AuthorizedException(UserErrorCode.NOT_AUTHORIZATION_ABOUT_CARD);
        }

        cardRepository.delete(card);
    }

    // 카드 순서 이동
    public void updateCardPosition(Long cardId, CardRequestDto requestDto, UserDetailsImpl userDetails) {

        Card card = findCard(cardId);
        card.updatePosition(requestDto.getPosition());
        cardRepository.save(card);
    }

    private BoardColumn findBoardColumn(Long columnId) {
        return boardColumnRepository.findById(columnId)
                .orElseThrow(() -> new NotFoundException(BoardColumnErrorCode.NOT_FOUND_COLUMN));
    }

    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException(UserErrorCode.NOT_FOUND_CARD));
    }

}
