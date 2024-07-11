package com.sparta.team15.controller;

import com.sparta.team15.dto.CardRequestDto;
import com.sparta.team15.dto.CardResponseDto;
import com.sparta.team15.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 카드 생성
    @PostMapping
    public ResponseEntity<String> createCard(@Valid @RequestBody CardRequestDto requestDto) {
        cardService.createCard(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("카드 생성 완료");
    }

    // 카드 전체 목록 조회
    @GetMapping
    public List<CardResponseDto> getAllCards(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {
        return cardService.getAllCards(page, size);
    }

    // 카드 상태별 조회
    @GetMapping("/status/{columnId}")
    public List<CardResponseDto> getCardsByStatus(@PathVariable Long columnId,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        return cardService.getCardsByStatus(columnId, page, size);
    }

    // 카드 작업자별 조회
    @GetMapping("/{author}")
    public List<CardResponseDto> getCardsByAuthor(@PathVariable String author,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        return cardService.getCardsByAuthor(author, page, size);
    }

    // 카드 수정
    @PutMapping("/{cardId}")
    public ResponseEntity<String> updateCard(@PathVariable Long cardId,
                                             @RequestBody CardRequestDto requestDto) {
        cardService.updateCard(cardId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("카드 수정 완료");
    }

    // 카드 삭제
    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> updateCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.status(HttpStatus.OK).body("카드 삭제 완료");
    }

    // 카드 순서 이동
    @PutMapping("/position/{cardId}")
    public ResponseEntity<String> updateCardPosition(@PathVariable Long cardId) {
        cardService.updateCardPosition(cardId);
        return ResponseEntity.status(HttpStatus.OK).body("카드 이동 완료");
    }


}
