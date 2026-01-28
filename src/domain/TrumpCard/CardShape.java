package domain.TrumpCard;

/**
 * 카드의 모양
 */
enum CardShape {
    SPADE('S'),
    HEART('H'),
    DIA('D'),
    CLUB('C');

    private final char SHAPE;


    CardShape(char shape){
        SHAPE = shape;
    }

    char getShape() { return SHAPE; }
}
