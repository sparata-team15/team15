package com.sparta.team15.controller;

import com.sparta.team15.dto.CardRequestDto;
import com.sparta.team15.dto.CardResponseDto;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<ResponseMessageDto> createCard(@Valid @RequestBody CardRequestDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.createCard(requestDto, userDetails);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.CARD_CREATED));
    }

    // 카드 전체 목록 조회
    @GetMapping
    public List<CardResponseDto> getAllCards(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getAllCards(page, size, userDetails);
    }

    // 카드 상태별 조회
    @GetMapping("/status/{columnId}")
    public List<CardResponseDto> getCardsByStatus(@PathVariable Long columnId,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCardsByStatus(columnId, page, size, userDetails);
    }

    // 카드 작업자별 조회
    @GetMapping("/{author}")
    public List<CardResponseDto> getCardsByAuthor(@PathVariable String author,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCardsByAuthor(author, page, size, userDetails);
    }

    // 카드 수정
    @PutMapping("/{cardId}")
    public ResponseEntity<ResponseMessageDto> updateCard(@PathVariable Long cardId,
                                             @RequestBody CardRequestDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.updateCard(cardId, requestDto, userDetails);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.CARD_UPDATED));
    }

    // 카드 삭제
    @DeleteMapping("/{cardId}")
    public ResponseEntity<ResponseMessageDto> updateCard(@PathVariable Long cardId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteCard(cardId, userDetails);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.CARD_DELETED));
    }

    // 카드 순서 이동
    @PutMapping("/position/{cardId}")
    public ResponseEntity<ResponseMessageDto> updateCardPosition(@PathVariable Long cardId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.updateCardPosition(cardId, userDetails);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.CARD_UPDATE_POSITION));
    }


}
