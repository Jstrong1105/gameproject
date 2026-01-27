package engine;

import domain.base.GameOption;
import util.InputHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameLauncher {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final InputHandler input = new InputHandler(br);

    private static final int gameCount = GameHub.values().length;
    private static final int optionNumber = gameCount + 1;
    private static final int exitNumber = gameCount + 2;
    private static final GameOption option = new GameOption();

    public static void main(String[] args) {

        boolean run = true;

        while(run) {
            System.out.println("====게임런처====");
            for (GameHub hub : GameHub.values()) {
                System.out.println(hub.getGameNumber() + ". " + hub.getGameName() + " : " + hub.getGameExplain());
            }

            System.out.println(optionNumber + ". 옵션");
            System.out.println(exitNumber + ". 종료");

            int answer = input.readIntRange("번호를 선택해주세요.", 1, exitNumber);

            if(answer == exitNumber){
                System.out.println("프로그램을 종료합니다.");
                run = false;
            }
            else if(answer == optionNumber){
                option();
            }
            else {
                GameHub.getHub(answer).getGame(input,option).run();
            }
        }
    }

    public static void option()
    {
        boolean run = true;
        while(run) {
            System.out.println("====게임옵션====");
            for (GameHub hub : GameHub.values()) {
                System.out.println(hub.getGameNumber() + ". " + hub.getGameName());
            }
            System.out.println(optionNumber + ". 뒤로가기");

            int answer = input.readIntRange("번호를 선택해주세요.", 1, optionNumber);

            if (answer == optionNumber) {
                run = false;
            }
            else{
                GameHub.getHub(answer);
            }
        }
    }
}
