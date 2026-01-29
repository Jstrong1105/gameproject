package util;

/**
 * 실행을 일정 시간동안 멈추는 녀석
 */
public class GameSleeper {

    public void sleepGame(int second){

        try{

            for(int i = 0; i < second; i++) {
                System.out.printf("\r%d초 남았습니다.",second-i);
                Thread.sleep(1000L);
            }
            System.out.printf("\r%d초 남았습니다.",0);
            Thread.sleep(500L);
            System.out.println();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}