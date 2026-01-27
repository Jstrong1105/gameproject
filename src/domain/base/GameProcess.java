package domain.base;

/**
 * 모든 게임이 구현해야하는 필수적인 요소를 나열한 인터페이스
 */
public interface GameProcess {

    // 실행 중 여부를 알려줄 메소드
    boolean isPlaying();

    // 외부에서 실행할 실행흐름
    void run();
}
