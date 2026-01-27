package domain.minesweeper;

/**
 * 지뢰 찾기에서 한칸을 나타내는 객체
 */
class Cell {

    // 현재 상태
    private CellStatus status;

    // 폭탄 여부
    private boolean mine;

    // 인접한 폭탄의 개수
    private int adjacentMines;

    // 생성자
    Cell() {
        status = CellStatus.CLOSED;
        mine = false;
        adjacentMines = 0;
    }

    // 폭탄 설정하기
    void setMine(boolean mine) {
        this.mine = mine;
    }

    // 인접한 폭탄 설정하기
    void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    // 폭탄 여부
    boolean isMine() { return mine; }

    // 인접 폭탄 개수
    int getAdjacentMines() { return adjacentMines; }

    // status 확인하기
    boolean isOpen() { return status == CellStatus.OPEN;}
    boolean isFlagged() { return status == CellStatus.FLAGGED;}
    boolean isClosed() { return status == CellStatus.CLOSED;}

    // 열기
    void openCell() {
        if(status == CellStatus.CLOSED)
            status = CellStatus.OPEN;
    }

    // 깃발 토글
    void flagCell() {
        if(status == CellStatus.CLOSED) {
            status = CellStatus.FLAGGED;
        }
        else if(status == CellStatus.FLAGGED){
            status = CellStatus.CLOSED;
        }
    }

    // 폭탄인 셀을 열기
    void openBoom()
    {
        if(mine)
            status = CellStatus.OPEN;
    }
}
