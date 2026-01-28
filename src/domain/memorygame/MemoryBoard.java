package domain.memorygame;

import domain.TrumpCard.Card;
import domain.TrumpCard.CardDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * 바닥에 깔려있는 카드들을 관리할 클래스
 */
class MemoryBoard {

    private final List<Card> board;

    MemoryBoard(int size, int pair){

        CardDeck dealerDeck = new CardDeck();
        board = new ArrayList<>();

        for(int i = 0; i < size; i++){

            Card card = dealerDeck.drawCard();

            board.add(card);

            for(int j = 1; j < pair; j++){
                board.add(card.copyCard());
            }
        }

        Collections.shuffle(board);
    }

    // 카드 선택하기
    Card drawCard(int number){

        return board.get(number);
    }

    // 카드 상태 확인하기
    boolean isOpen(int number){

        return board.get(number).isOpen();
    }
}
