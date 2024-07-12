package com.sparta.team15.service;

import com.sparta.team15.dto.CardRequestDto;
import com.sparta.team15.dto.CardResponseDto;
import com.sparta.team15.entity.Card;
import com.sparta.team15.repository.CardRepository;
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

    // 카드 생성
    public void createCard(CardRequestDto requestDto) {
        Card card = new Card(
                requestDto.getAuthor(),
                requestDto.getColumnId(),
                requestDto.getContent(),
                requestDto.getDescription(),
                requestDto.getDate()
        );
        cardRepository.save(card);
    }

    // 카드 전체 목록 조회
    public List<CardResponseDto> getAllCards(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findAll(pageable).stream()
                .map(CardResponseDto::new).toList();
    }

    // 카드 상태별 조회
    public List<CardResponseDto> getCardsByStatus(Long columnId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findByColumnId(columnId, pageable).stream()
                .map(CardResponseDto::new).toList();
    }

    // 카드 작업자별 조회
    public List<CardResponseDto> getCardsByAuthor(String author, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return cardRepository.findByAuthor(author, pageable).stream()
                .map(CardResponseDto::new).toList();
    }

    // 카드 수정
    public void updateCard(Long cardId, CardRequestDto requestDto) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        card.update(requestDto.getAuthor(), requestDto.getContent(), requestDto.getDescription(), requestDto.getDate());
        cardRepository.save(card);

    }

    // 카드 삭제
    public void deleteCard(Long cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        cardRepository.delete(card);
    }

    // 카드 순서 이동
    public void updateCardPosition(Long cardId) {

    }

}
