package domain.TrumpCard;

import java.util.ArrayList;
import java.util.List;

public class CardPrinter {

    private final char[] NUMBER_LIST = { '2','3','4','5','6','7','8','9','T','J','Q','K','A'};

    public void printCard(List<Card> cardDeck){

        int num = cardDeck.size();

        for(int i = 0; i < num; i++){System.out.print("┌───────┐ ");}
        System.out.println();
        for(Card card : cardDeck)
        {
            if(card.isOpen()) {
                System.out.printf("│ %c     │ ", card.getShape());
            }
            else {
                System.out.printf("│ %c     │ ", '?');
            }
        }
        System.out.println();
        for(Card card : cardDeck)
        {
            if(card.isOpen()) {
                System.out.printf("│   %c   │ ", NUMBER_LIST[card.getNumber() - 2]);
            }
            else{
                System.out.printf("│   %c   │ ", '?');
            }
        }
        System.out.println();
        for(Card card : cardDeck)
        {
            if(card.isOpen()) {
                System.out.printf("│     %c │ ", card.getShape());
            }
            else {
                System.out.printf("│     %c │ ", '?');
            }
        }
        System.out.println();
        for(int i = 0; i < num; i++){System.out.print("└───────┘ ");}
    }
}
