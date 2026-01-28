package domain.base;

/**
 * 게임의 결과를 담을 객체
 */
public class GameResult {

    private final GameResultType RESULT;
    // 지금은 결과 자체만 담고 있지만 나중에 점수나 시간 등을 추가할 예정이다.

    public GameResult(GameResultType result)
    {
        RESULT = result;
    }

    public boolean isWin() { return RESULT == GameResultType.WIN; }
    public boolean isLose() { return RESULT == GameResultType.LOSE; }
    public boolean isDraw() { return RESULT == GameResultType.DRAW; }
    public boolean isFold() { return RESULT == GameResultType.FOLD; }

}
