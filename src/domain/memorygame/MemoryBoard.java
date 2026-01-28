package domain.memorygame;

import domain.TrumpCard.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 바닥에 깔려있는 카드들을 관리할 클래스
 */
class MemoryBoard {

    List<Card> board;

    MemoryBoard(){
        board = new ArrayList<>();
    }

    // 카드 선택하기
    Card flipCard(int number){

        return board.get(number);
    }

    // 카드 상태 확인하기
    boolean isOpen(int number){

        return board.get(number).isOpen();
    }

    // 카드 열기
    void openCard(int number){

        board.get(number).openCard();
    }

}
