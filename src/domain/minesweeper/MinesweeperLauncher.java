package domain.minesweeper;

import domain.base.GameResult;
import domain.base.GameResultType;
import domain.base.GameTemplate;
import util.InputHandler;

public class MinesweeperLauncher extends GameTemplate {

    private static final int OPEN_NUMBER = 1;
    private final int FLAG_NUMBER = 2;
    private final int BOARD_SIZE;
    private final int WEIGHT;
    private static final int MIN_LEVEL = 1;
    private static final int MAX_LEVEL = 3;
    private static final int[] LEVEL_LIST = {10,5,4};

    private MinesweeperBoard board;

    private int playerRow;
    private int playerCol;
    private int openFlag;
    private boolean first;

    public MinesweeperLauncher(MinesweeperOption option)
    {
        BOARD_SIZE = option.getSize();
        WEIGHT = option.getWeight();
    }

    @Override
    protected void initialize() {

        InputHandler.clearBuffer();

        System.out.println("지뢰찾기 게임입니다.");

        int level = InputHandler.readIntRange("난이도를 입력해주세요.",MIN_LEVEL,MAX_LEVEL);

        int boomCount = BOARD_SIZE * BOARD_SIZE / LEVEL_LIST[level-1] * WEIGHT;

        board = new MinesweeperBoard(BOARD_SIZE,boomCount);

        first = true;
    }

    @Override
    protected void render() {

        cleanScreen();

        for(int i = 0; i < BOARD_SIZE * 2; i++) {
            System.out.print("=");
        }

        System.out.print("지뢰찾기");

        for(int i = 0; i < BOARD_SIZE * 2; i++) {
            System.out.print("=");
        }

        System.out.println();

        for(int i = 0; i < BOARD_SIZE * 4 + 6; i++) {
            System.out.print("=");
        }

        System.out.println();

        board.printBoard();
    }

    @Override
    protected void handleInput() {

        InputHandler.clearBuffer();

        playerRow = 0;
        playerCol = 0;
        openFlag = 0;

        playerRow = InputHandler.readIntRange("열 번호 입력",1,BOARD_SIZE);
        playerCol = InputHandler.readIntRange("행 번호 입력",1,BOARD_SIZE);
        openFlag = InputHandler.readIntRange("오픈("+ OPEN_NUMBER +")/깃발("+ FLAG_NUMBER +")",OPEN_NUMBER,FLAG_NUMBER);

        playerRow--;
        playerCol--;
    }

    @Override
    protected void update() {

        if(openFlag == FLAG_NUMBER)
        {
            board.flagCell(playerRow,playerCol);
            return;
        }

        if(first){
            first = false;
            board.firstMine(playerRow,playerCol);
        }

        if(board.openCell(playerRow,playerCol)) {

            finish(new GameResult(GameResultType.LOSE));
        }
        else {
            if(board.clearCheck()){
                finish(new GameResult(GameResultType.WIN));
            }
        }
    }

    @Override
    protected void finish(GameResult result) {
        board.openBoom();
        render();
        System.out.println("게임이 종료되었습니다.");

        if(result.isWin()){
            System.out.println("축하합니다. 모든 폭탄을 찾아냈습니다.");
        }
        else{
            System.out.println((playerRow+1) +"열 " + (playerCol+1) + "행은 폭탄입니다.");
        }

        setPlaying(false);
    }

    // 화면 청소하기
    private void cleanScreen() {

        for(int i = 0; i < 30; i++)
        {
            System.out.println();
            try{
                Thread.sleep(5);
            }
            catch (InterruptedException e) {
                 throw new RuntimeException(e);
            }
        }
    }
}
