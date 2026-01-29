package engine;

import domain.base.GameOption;
import domain.memorygame.MemoryGameLauncher;
import util.GameSleeper;
import util.InputHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameLauncher {

    private static final GameOption GAME_OPTION = new GameOption();
    private static InputHandler input;
    private static final int OPTION = GameHub.values().length + 1;
    private static final int EXIT = OPTION + 1;

   public static void main(String[] args){

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       input = new InputHandler(br);
       boolean run = true;
       GameSleeper sleeper = new GameSleeper();
       System.out.println("로딩중입니다.");
       sleeper.sleepGame(3);
       while(run) {

           input.clearBuffer();

           System.out.println("\n\n");

           System.out.println("====게임 런처====");

           for (GameHub hub : GameHub.values()) {
               System.out.println(hub.getNumber() + ". " + hub.getName() + " : " + hub.getExplain());
           }

           System.out.println(OPTION + ". 옵션");
           System.out.println(EXIT + ". 종료");

           int answer = input.readIntRange("번호를 선택해주세요",0,EXIT);

           if(answer == 0){
               new MemoryGameLauncher(input,GAME_OPTION.getMemoryGameOption()).run();
           }

           if(answer == EXIT){
               System.out.println("프로그램을 종료합니다.");
               run = false;
           }
           else if(answer == OPTION){
               setOption();
           }
           else {
                GameHub.getGameHub(answer).getGame(input,GAME_OPTION).run();
           }
       }
    }

    static void setOption()
    {
        boolean run = true;

        while(run) {

            System.out.println("\n\n");

            System.out.println("====게임 옵션====");

            for (GameHub hub : GameHub.values()) {
                System.out.println(hub.getNumber() + ". " + hub.getName() + " 옵션");
            }

            System.out.println(OPTION + ". 뒤로가기");

            int answer = input.readIntRange("번호를 선택해주세요",1,OPTION);


            if(answer == OPTION){
                run = false;
            }
            else {
                GameHub.getGameHub(answer).setOption(input,GAME_OPTION);
            }
        }
    }
}
