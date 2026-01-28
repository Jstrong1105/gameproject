package domain.TrumpCard;

/**
 * 트럼프 카드 한장
 */
public class Card {

    private CardStatus status;
    private final CardShape SHAPE;
    private final int NUMBER;
    static final int MIN_NUMBER = 2;
    static final int MAX_NUMBER = 14;

    // 생성은 같은 패키지에 있는 CardDeck에서만 할 수 있다.
    // 다만 deck에서 뽑은 카드 한장의 메소드에는 접근이 가능하다.
    Card(CardShape shape, int number){

        if(number < MIN_NUMBER || number > MAX_NUMBER){
            throw new IllegalArgumentException("잘못된 카드 생성입니다.");
        }

        SHAPE = shape;
        NUMBER = number;
        status = CardStatus.HIDDEN;
    }

    public char getShape() { return SHAPE.getShape(); }
    public int getNumber() {return NUMBER;}

    public void openCard(){
        status = CardStatus.OPEN;
    }

    public void hiddenCard(){
        status = CardStatus.HIDDEN;
    }

    public boolean isOpen(){
        return status == CardStatus.OPEN;
    }

    public boolean isHidden(){
        return status == CardStatus.HIDDEN;
    }

    public Card copyCard(){
        return new Card(SHAPE,NUMBER);
    }

    @Override
    public boolean equals(Object o){

        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if(this.getClass() != o.getClass()){
            return false;
        }

        Card card = (Card) o;

        return NUMBER == card.NUMBER && SHAPE == card.SHAPE;
    }

    @Override
    public int hashCode(){
        return NUMBER + SHAPE.ordinal()  * 31;
    }
}
