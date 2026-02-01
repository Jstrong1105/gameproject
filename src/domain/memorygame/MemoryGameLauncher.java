package domain.memorygame;

import domain.TrumpCard.Card;
import domain.TrumpCard.CardPrinter;
import domain.base.GameResult;
import domain.base.GameResultType;
import domain.base.GameTemplate;
import util.GameSleeper;
import util.InputHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 게임 런처
 */
public class MemoryGameLauncher extends GameTemplate {

    public MemoryGameLauncher(MemoryGameOption option){
        SIZE = option.getSize();
        PAIR = option.getPair();
        WEIGHT = option.getWeight();
        printer = new CardPrinter();
    }

    private final int SIZE;
    private final int PAIR;
    private final int WEIGHT;

    private final CardPrinter printer;

    private MemoryBoard board;

    private List<Card> playerCard;
    private int matchCardCount;

    @Override
    protected void initialize() {

        board = new MemoryBoard(SIZE,PAIR);
        playerCard = new ArrayList<>();
        matchCardCount = 0;

        System.out.println("메모리 게임입니다.");

        for(int i = 0; i < SIZE*PAIR; i++){
            board.drawCard(i).openCard();
        }

        InputHandler.readString("준비하세요! 엔터를 누르면 카드가 보여집니다.");
        
        printCard();

        // 10초 * WEIGHT 초
        GameSleeper.sleepGame(10 * WEIGHT);

        for(int i = 0; i < SIZE*PAIR; i++){
            board.drawCard(i).hiddenCard();
        }
    }

    @Override
    protected void render() {

        clearScreen();
        printCard();
    }

    @Override
    protected void handleInput() {

        InputHandler.clearBuffer();

        playerCard.clear();

        while(playerCard.size() < PAIR){

            int player = InputHandler.readIntRange("카드를 선택해주세요.",1,SIZE*PAIR);

            player--;

            if(board.drawCard(player).isHidden()){
                board.drawCard(player).openCard();
                playerCard.add(board.drawCard(player));
                clearScreen();
                printCard();
            }
            else{
                System.out.println("이미 오픈된 카드입니다.");
            }
        }
    }

    @Override
    protected void update() {

        for(int i = 0; i < PAIR - 1; i++){
            if(!playerCard.get(i).equals(playerCard.get(i+1))){

                System.out.println("다른 카드입니다.");

                // 다른 카드가 한장이라도 섞여 있다면
                for(Card card : playerCard){
                    card.hiddenCard();
                }
                GameSleeper.sleepGame(3 * WEIGHT);

                return;
            }
        }

        // 모두 같은 카드라면
        System.out.println("같은 카드입니다.");

        GameSleeper.sleepGame(1);

        matchCardCount += PAIR;

        if(matchCardCount >= PAIR * SIZE){
            finish(new GameResult(GameResultType.WIN));
        }
    }

    @Override
    protected void finish(GameResult result) {

        System.out.println("모든 카드를 맞췄습니다.");
        setPlaying(false);
    }
    
    // 카드를 PAIR 행 SIZE 열로 출력하는 메소드
    private void printCard(){

        List<Card> cardList = new ArrayList<>();

        for(int i = 0; i < PAIR; i++){

            cardList.clear();

            System.out.println();

            for(int j = 0; j < SIZE; j++){

                cardList.add(board.drawCard(j+i*SIZE));
            }

            printer.printCard(cardList);
        }

        System.out.println();
    }

    // 화면 정리 메소드
    private void clearScreen(){
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
    }
}
