package engine;

import domain.base.GameOption;
import util.InputHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameLauncher {

    private static final GameOption gameOption = new GameOption();
    private static InputHandler input;
    private static final int option = GameHub.values().length + 1;
    private static final int exit = option + 1;

   public static void main(String[] args){

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       input = new InputHandler(br);
       boolean run = true;

       while(run) {

           System.out.println("====게임 런처====");

           for (GameHub hub : GameHub.values()) {
               System.out.println(hub.getNumber() + ". " + hub.getName() + " : " + hub.getExplain());
           }

           System.out.println(option + ". 옵션");
           System.out.println(exit + ". 종료");

           int answer = input.readIntRange("번호를 선택해주세요",1,exit);

           if(answer == exit){
               System.out.println("프로그램을 종료합니다.");
               run = false;
           }
           else if(answer == option){
               setOption();
           }
           else {
                GameHub.getGameHub(answer).getGame(input,gameOption).run();
           }
       }
    }

    static void setOption()
    {
        boolean run = true;

        while(run) {

            System.out.println("====게임 옵션====");

            for (GameHub hub : GameHub.values()) {
                System.out.println(hub.getNumber() + ". " + hub.getName() + " : " + hub.getExplain());
            }

            System.out.println(option + ". 뒤로가기");

            int answer = input.readIntRange("번호를 선택해주세요",1,option);


            if(answer == option){
                run = false;
            }
            else {
                GameHub.getGameHub(answer).setOption(input,gameOption);
            }
        }
    }
}
