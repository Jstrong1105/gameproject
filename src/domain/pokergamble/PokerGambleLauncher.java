package domain.pokergamble;

import domain.TrumpCard.CardDeck;
import domain.base.GameResult;
import domain.base.GameResultType;
import domain.base.GameTemplate;
import util.InputHandler;

public class PokerGambleLauncher extends GameTemplate {

    public PokerGambleLauncher(PokerGambleOption option){
        five = option.getFS();
        weight = option.getWeight();
        targetCoin = option.getTargetCoin();
    }

    private final boolean five;
    private final int weight;
    private final int targetCoin;
    private static final int BASIC_BET_COIN = 5;
    private static final int[] LEVEL_LIST = { 4,5,10 };

    private int cardCount;
    private int playerCoin;
    private int totalBetCoin;
    private int currentBetCoin;

    private HandCard playerCard;
    private HandCard cpuCard;
    private CardDeck dealerDeck;

    private int[] openList;

    private PokerRankingResult playerResult;
    private PokerRankingResult cpuResult;

    @Override
    protected void initialize() {

        System.out.println("포커겜블 게임입니다.");

        if(five){
            cardCount = 5;
            openList = new int[]{ 1,2 };
        }
        else{
            cardCount = 7;
            openList = new int[]{ 1,2,3 };
        }

        int minLevel = 1;
        int maxLevel = 3;

        InputHandler.clearBuffer();
        int level = InputHandler.readIntRange("난이도를 입력해주세요.",minLevel,maxLevel);
        playerCoin = targetCoin / LEVEL_LIST[level-1];

        roundInitialize();
    }

    // 라운드 초기화
    private void roundInitialize(){

        totalBetCoin = 0;
        currentBetCoin = 0;

        playerCard = new HandCard();
        cpuCard = new HandCard();
        dealerDeck = new CardDeck();
        drawCard();
        drawCard();

        if(playerCoin >= BASIC_BET_COIN){
            playerCoin -= BASIC_BET_COIN;
            totalBetCoin += BASIC_BET_COIN;
        }
        else {
            totalBetCoin += playerCoin;
            playerCoin = 0;
        }
    }

    @Override
    protected void render() {

        for(int i = 0; i < playerCard.countCard(); i++){
            playerCard.openCard(i);
        }
        for(int i : openList){
            if(cpuCard.countCard() > i){
                cpuCard.openCard(i);
            }
        }

        cpuCard.printCard();
        System.out.println(" 컴퓨터의 카드");
        System.out.println();
        playerCard.printCard();
        System.out.println(" 당신의 카드");
        System.out.println("목표 코인 : " + targetCoin);
        System.out.println("당신의 코인 : " + playerCoin);
        System.out.println("베팅 코인 : " + totalBetCoin);
    }

    @Override
    protected void handleInput() {

        InputHandler.clearBuffer();
        currentBetCoin = InputHandler.readIntRange("베팅할 코인을 입력해주세요.",0,playerCoin);
        playerCoin -= currentBetCoin;
        totalBetCoin += currentBetCoin;
    }

    @Override
    protected void update() {

        if(currentBetCoin == 0 && playerCoin > 0){
            finish(new GameResult(GameResultType.FOLD));
        }

        else {
            if(playerCard.countCard() >= cardCount){
                playerResult = playerCard.evaluateRanking();
                cpuResult = cpuCard.evaluateRanking();

                int result = playerResult.compareTo(cpuResult);

                if(result > 0){
                    finish(new GameResult(GameResultType.WIN));
                }
                else if(result < 0){
                    finish(new GameResult(GameResultType.LOSE));
                }
                else {
                    finish(new GameResult(GameResultType.DRAW));
                }
            }
            else {
                drawCard();
            }
        }
    }

    @Override
    protected void finish(GameResult result) {

        for(int i = 0; i < cpuCard.countCard(); i++) {
            cpuCard.openCard(i);
        }

        cpuCard.printCard();
        System.out.println(cpuResult.getRankingName());

        playerCard.printCard();
        System.out.println(playerResult.getRankingName());

        if(result.isWin()){
            System.out.println("승리했습니다.");
            playerCoin += totalBetCoin * 2 * weight;
        }
        else if(result.isDraw()){
            System.out.println("무승부입니다.");
            playerCoin += totalBetCoin;
        }
        else if(result.isLose()){
            System.out.println("패배했습니다.");
        }
        else if(result.isFold()){
            System.out.println("기권했습니다.");
        }

        if(playerCoin >= targetCoin){
            System.out.println("축하합니다. 목표코인을 달성했습니다.");
            setPlaying(false);
        }
        else if(playerCoin <= 0){
            System.out.println("모든 코인을 소진했습니다.");
            setPlaying(false);
        }

        InputHandler.readString("엔터를 눌러 계속");

        roundInitialize();
    }

    // 카드 나눠주는 메소드
    private void drawCard(){
        playerCard.drawCard(dealerDeck.drawCard());
        cpuCard.drawCard(dealerDeck.drawCard());
    }
}
