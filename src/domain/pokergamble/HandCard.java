package domain.pokergamble;

import domain.TrumpCard.Card;

import java.util.List;
import java.util.ArrayList;

/**
 * 각각의 플레이어가 가지고 있는 카드 더미를 관리할 클래스
 */
class HandCard {

    private final List<Card> handCard;

    HandCard(){
        handCard = new ArrayList<>();
    }

    // 덱에 카드 추가하기
    void drawCard(Card card){
        handCard.add(card);
    }

    // 카드 개수 반환하기
    int countCard(){
        return handCard.size();
    }

    // 카드 공개하기
    void openCard(int index){
        handCard.get(index).openCard();
    }
}
