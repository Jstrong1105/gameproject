package domain.base;

/**
 * 게임의 결과를 담을 객체
 */
public class GameResult {

    private final GameResultType result;
    // 지금은 결과 자체만 담고 있지만 나중에 점수나 시간 등을 추가할 예정이다.

    public GameResult(GameResultType result)
    {
        this.result = result;
    }

    public boolean isWin() { return result == GameResultType.WIN; }
    public boolean isLose() { return result == GameResultType.LOSE; }
    public boolean isDraw() { return result == GameResultType.DRAW; }
    public boolean isFold() { return result == GameResultType.FOLD; }

}
