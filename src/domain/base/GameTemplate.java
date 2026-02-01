package domain.base;

import util.InputHandler;

/**
 * 모든 게임이 상속받을 추상 클래스로 모든 게임이 공통적으로 가지는 부분을 구현한
 * 템플릿 메소드 패턴.
 */
public abstract class GameTemplate implements GameProcess{

    // 실행 중 여부
    private boolean playing = false;

    // 외부에서 실행할 메소드 (실행 흐름)
    @Override
    public final void run()
    {
        do
        {
            setPlaying(true);
            initialize();

            while(isPlaying())
            {
                render();
                handleInput();
                update();
            }
        }
        while(restart());
    }

    // 다시 시작 메소드
    private boolean restart()
    {
        while(true)
        {
            String answer = InputHandler.readString("다시 시작하시겠습니까 ? (Y/N)");
            if(answer.equalsIgnoreCase("Y"))
            {
                return true;
            }
            else if(answer.equalsIgnoreCase("N"))
            {
                InputHandler.readString("게임을 종료합니다.");
                return false;
            }
            else
            {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    @Override
    public final boolean isPlaying()
    {
        return playing;
    }

    protected final void setPlaying(boolean playing)
    {
        this.playing = playing;
    }

    // 시작 전 수행하는 초기화
    protected abstract void initialize();

    // 화면 출력
    protected abstract void render();

    // 사용자 입력
    protected abstract void handleInput();

    // 게임 상태 갱신
    protected abstract void update();

    // 게임 종료 시 사용
    protected abstract void finish(GameResult result);

}
