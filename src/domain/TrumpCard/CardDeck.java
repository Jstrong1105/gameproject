package domain.TrumpCard;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {

    private final List<Card> cardDeck;

    public CardDeck(){
        cardDeck = new ArrayList<>();

        for(CardShape shape : CardShape.values()){
            for(int i = Card.MIN_NUMBER; i <= Card.MAX_NUMBER; i++){
                cardDeck.add(new Card(shape,i));
            }
        }
        Collections.shuffle(cardDeck);
    }

    public Card drawCard(){

        if(cardDeck.isEmpty()){
            throw new IllegalStateException("카드 덱이 비어있습니다.");
        }

        return cardDeck.remove(cardDeck.size() - 1);
    }
}
