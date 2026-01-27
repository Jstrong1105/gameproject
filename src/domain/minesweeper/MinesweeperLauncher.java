package domain.minesweeper;

import domain.base.GameResult;
import domain.base.GameResultType;
import domain.base.GameTemplate;
import util.InputHandler;

public class MinesweeperLauncher extends GameTemplate {

    private final int BOARD_SIZE;
    private final int WEIGHT;
    private static final int MIN_LEVEL = 1;
    private static final int MAX_LEVEL = 3;
    private static final int[] LEVEL_LIST = {1,10,5,4};

    private MinesweeperBoard board;

    private int playerRow;
    private int playerCol;
    private int openFlag;
    private boolean first;

    public MinesweeperLauncher(InputHandler input, MinesweeperOption option)
    {
        super(input);
        BOARD_SIZE = option.getSize();
        WEIGHT = option.getWeight();
    }

    @Override
    protected void initialize() {

        System.out.println("지뢰찾기 게임입니다.");

        int level = input.readIntRange("난이도를 입력해주세요.",MIN_LEVEL,MAX_LEVEL);

        int boomCount = BOARD_SIZE * BOARD_SIZE / LEVEL_LIST[level] * WEIGHT;

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

        playerRow = 0;
        playerCol = 0;
        openFlag = 0;

        playerRow = input.readIntRange("열 번호 입력",1,BOARD_SIZE);
        playerCol = input.readIntRange("행 번호 입력",1,BOARD_SIZE);
        openFlag = input.readIntRange("오픈(1)/깃발(2)",1,2);

        playerRow--;
        playerCol--;
    }

    @Override
    protected void update() {

        if(openFlag == 2)
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
