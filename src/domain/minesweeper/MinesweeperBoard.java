package domain.minesweeper;

import java.util.Random;

/**
 * 지뢰찾기 게임 보드판
 */
class MinesweeperBoard {

    // 상수
    private final int BOARD_SIZE;
    private static final int[] LX = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] LY = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final char CLOSED_SHAPE = '■';
    private static final char[] OPENED_SHAPE = {'□', '1', '2', '3', '4', '5', '6', '7', '8'};
    private static final char FLAGGED_SHAPE = '§';
    private static final char MINE_SHAPE = '*';

    // 랜덤
    private final Random rd;

    // 보드판
    private final Cell[][] board;

    // 폭탄의 수
    private final int BOOM_COUNT;

    // 열린 칸의 수
    private int openCellCount;

    MinesweeperBoard(int size, int boomCount) {
        rd = new Random();
        BOARD_SIZE = size;
        BOOM_COUNT = boomCount;
        openCellCount = 0;
        board = new Cell[BOARD_SIZE][BOARD_SIZE];

        // 새로운 보드판 끼우기
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell();
            }
        }

        // 폭탄 랜덤하게 설정하기
        while (boomCount > 0) {
            int x = rd.nextInt(BOARD_SIZE);
            int y = rd.nextInt(BOARD_SIZE);

            if (!board[x][y].isMine()) {
                board[x][y].setMine(true);
                boomCount--;
            }
        }
    }

    // 처음 입력한 위치가 폭탄일때 다른 위치로 폭탄 옮기기
    void firstMine(int x, int y) {
        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException("범위를 벗어났습니다.");
        }

        if (board[x][y].isMine()) {
            while (true) {
                int nx = rd.nextInt(BOARD_SIZE);
                int ny = rd.nextInt(BOARD_SIZE);

                if (!board[nx][ny].isMine()) {
                    board[nx][ny].setMine(true);
                    board[x][y].setMine(false);
                    break;
                }
            }
        }
    }

    // 깃발 토글하기
    void flagCell(int x, int y) {
        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException("범위를 벗어났습니다.");
        }

        board[x][y].flagCell();
    }


    // 플레이어가 입력한 칸을 받아서 처리하기
    boolean openCell(int x, int y) {

        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException("범위를 벗어났습니다.");
        }

        // 이미 열린 칸이라면 리턴
        if (board[x][y].isOpen()) {
            return false;
        }

        // 폭탄이라면 리턴
        if (board[x][y].isMine()) {
            return true;
        }

        // 열리지 않았고 폭탄이 아닌 칸인 경우
        else {
            int adjacentMines = findMines(x, y);
            board[x][y].setAdjacentMines(adjacentMines);
            board[x][y].openCell();
            openCellCount++;

            // 인접한 폭탄이 0개인 경우 주변 8칸을 연다.
            if (adjacentMines == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + LX[i];
                    int ny = y + LY[i];

                    // 보드판 범위를 벗어났다면 열지 않는다.
                    if (!isOutOfBounds(nx, ny)) {
                        openCell(nx, ny);
                    }
                }
            }

            return false;
        }
    }

    // 인접한 8칸에서 지뢰의 개수를 찾기
    private int findMines(int x, int y) {
        int num = 0;

        for (int i = 0; i < 8; i++) {
            int nx = x + LX[i];
            int ny = y + LY[i];

            if (!isOutOfBounds(nx, ny) && board[nx][ny].isMine()) {
                num++;
            }
        }

        return num;
    }

    // 입력 값이 범위를 벗어났는지 확인하기
    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE;
    }

    // 보드판 출력하기
    void printBoard() {
        // 행 번호 출력
        System.out.print("   행 ");

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("%2d ", i + 1);
        }

        System.out.println();

        // 열 번호와 각각의 셀 출력
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("%2d 열 ", i + 1);

            for (int j = 0; j < BOARD_SIZE; j++) {
                char ch;
                if (board[i][j].isClosed()) {
                    ch = CLOSED_SHAPE;
                }
                else if (board[i][j].isFlagged()) {
                    ch = FLAGGED_SHAPE;
                }
                else {
                    if (board[i][j].isMine()) {
                        ch = MINE_SHAPE;
                    }
                    else {
                        ch = OPENED_SHAPE[board[i][j].getAdjacentMines()];
                    }
                }
                System.out.printf("%2c ", ch);
            }
            System.out.println();
        }
    }

    // 클리어 체크
    boolean clearCheck() {
        return openCellCount >= BOARD_SIZE * BOARD_SIZE - BOOM_COUNT;
    }

    // 모든 폭탄 개방하기
    void openBoom()
    {
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            for(int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j].openBoom();
            }
        }
    }
}
