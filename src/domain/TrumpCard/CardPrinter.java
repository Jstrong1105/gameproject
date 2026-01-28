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

    public static void main(String[] args) {

        CardDeck dealer = new CardDeck();

        List<Card> deck = new ArrayList<>();

        Card card1 = dealer.drawCard();
        Card card2 = dealer.drawCard();
        Card card3 = dealer.drawCard();
        Card card4 = dealer.drawCard();
        Card card5 = dealer.drawCard();

        card1.openCard();
        card2.openCard();
        card3.openCard();
        card4.openCard();
        card5.openCard();

        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);

        CardPrinter cp = new CardPrinter();

        cp.printCard(deck);
    }
}
