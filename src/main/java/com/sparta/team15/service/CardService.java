package com.sparta.team15.service;

import com.sparta.team15.dto.CardRequestDto;
import com.sparta.team15.dto.CardResponseDto;
import com.sparta.team15.entity.BoardColumn;
import com.sparta.team15.entity.Card;
import com.sparta.team15.entity.User;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.repository.BoardColumnRepository;
import com.sparta.team15.repository.CardRepository;
import com.sparta.team15.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final BoardColumnRepository boardColumnRepository;

    // 카드 생성
    public void createCard(CardRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        BoardColumn boardColumn = boardColumnRepository.findById(requestDto.getColumnId())
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_COLUMN_ID.getMessage()));

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
        User user = userDetails.getUser();

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findAll(pageable).stream()
                .map(CardResponseDto::new).toList();
    }

    // 카드 상태별 조회
    public List<CardResponseDto> getCardsByStatus(Long columnId, int page, int size, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        BoardColumn boardColumn = boardColumnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_COLUMN_ID.getMessage()));

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findByBoardColumn(boardColumn, pageable).stream()
                .map(CardResponseDto::new).toList();
    }

    // 카드 작업자별 조회
    public List<CardResponseDto> getCardsByAuthor(String author, int page, int size, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findByAuthor(author, pageable).stream()
                .map(CardResponseDto::new).toList();
    }

    // 카드 수정
    public void updateCard(Long cardId, CardRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_CARD_ID.getMessage()));

        if (card.getUser().getId() != user.getId()) {
            throw new IllegalArgumentException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        card.update(requestDto.getAuthor(), requestDto.getContent(), requestDto.getDescription(), requestDto.getDate());
        cardRepository.save(card);

    }

    // 카드 삭제
    public void deleteCard(Long cardId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException(MessageEnum.INVALID_CARD_ID.getMessage()));

        if (card.getUser().getId() != user.getId()) {
            throw new IllegalArgumentException(MessageEnum.UNAUTHORIZED_ACTION.getMessage());
        }

        cardRepository.delete(card);
    }

    // 카드 순서 이동
    public void updateCardPosition(Long cardId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

    }

}
