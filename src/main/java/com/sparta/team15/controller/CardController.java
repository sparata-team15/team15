package com.sparta.team15.controller;

import com.sparta.team15.dto.CardRequestDto;
import com.sparta.team15.dto.CardResponseDto;
import com.sparta.team15.dto.ResponseMessageDto;
import com.sparta.team15.enums.MessageEnum;
import com.sparta.team15.security.UserDetailsImpl;
import com.sparta.team15.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 카드 생성
    @PostMapping
    public ResponseEntity<ResponseMessageDto> createCard(
        @Valid @RequestBody CardRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.createCard(requestDto, userDetails);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.CARD_CREATED));
    }

    // 카드 전체 목록 조회
    @GetMapping
    public List<CardResponseDto> getAllCards(@RequestParam @Min(1) int page,
                                             @RequestParam @Min(1) @Max(5) int size,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getAllCards(page-1, size, userDetails);
    }

    // 카드 상태별 조회
    @GetMapping("/status/{columnId}")
    public List<CardResponseDto> getCardsByStatus(@PathVariable Long columnId,
                                                  @RequestParam @Min(1) int page,
                                                  @RequestParam @Min(1) @Max(5) int size,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCardsByStatus(columnId, page-1, size, userDetails);
    }

    // 카드 작업자별 조회
    @GetMapping("/{author}")
    public List<CardResponseDto> getCardsByAuthor(@PathVariable String author,
                                                  @RequestParam @Min(1) int page,
                                                  @RequestParam @Min(1) @Max(5) int size,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCardsByAuthor(author, page-1, size, userDetails);
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
        @RequestBody CardRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.updateCardPosition(cardId, requestDto, userDetails);
        return ResponseEntity.ok(new ResponseMessageDto(MessageEnum.CARD_UPDATE_POSITION));
    }


}
