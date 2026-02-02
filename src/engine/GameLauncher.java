package engine;

import domain.base.GameOption;
import playerecord.PlayerRecord;
import playerecord.RecordSaveLoad;
import util.GameSleeper;
import util.InputHandler;

public class GameLauncher {

    private static final GameOption GAME_OPTION = new GameOption();
    private static final int OPTION = GameHub.values().length + 1;
    private static final int EXIT = OPTION + 1;

    public static String playerId;

    public static void main(String[] args){

       boolean run;
       System.out.println("로딩중입니다.");
       GameSleeper.sleepGame(3);

       run = login();

       while(run) {

           InputHandler.clearBuffer();

           System.out.println("\n\n");

           System.out.println("====게임 런처====");

           for (GameHub hub : GameHub.values()) {
               System.out.println(hub.getNumber() + ". " + hub.getName() + " : " + hub.getExplain());
           }

           System.out.println(OPTION + ". 옵션");
           System.out.println(EXIT + ". 종료");

           int answer = InputHandler.readIntRange("번호를 선택해주세요",1,EXIT);

           if(answer == EXIT){
               System.out.println("프로그램을 종료합니다.");
               run = false;
           }
           else if(answer == OPTION){
               setOption();
           }
           else {
                GameHub.getGameHub(answer).getGame(GAME_OPTION).run();
           }
       }
    }

    static boolean login(){

        boolean run = true;

        RecordSaveLoad.loadRecord();

        InputHandler.clearBuffer();

        while(run){

            int num = InputHandler.readIntRange("1. 로그인 / 2. 회원가입",1,2);
            if(num == 1){
                playerId = InputHandler.readString("아이디를 입력하세요.");
                if(RecordSaveLoad.record.containsKey(playerId)){
                    System.out.println("로그인 성공");
                    run = false;
                }
                else{
                    System.out.println("존재하지 않는 아이디입니다.");
                }
            }
            else{
                boolean running = true;
                while(running) {
                    String newId = InputHandler.readString("가입할 아이디를 입력하세요.");

                    if (RecordSaveLoad.record.containsKey(newId)) {
                        System.out.println("이미 존재하는 아이디입니다.");
                    }
                    else {
                        RecordSaveLoad.record.put(newId,new PlayerRecord());
                        System.out.println("회원가입 완료");
                        RecordSaveLoad.saveRecord();
                        running = false;
                    }
                }
            }
        }

        return true;
    }

    static void setOption() {

        boolean run = true;

        while(run) {

            System.out.println("\n\n");

            System.out.println("====게임 옵션====");

            for (GameHub hub : GameHub.values()) {
                System.out.println(hub.getNumber() + ". " + hub.getName() + " 옵션");
            }

            System.out.println(OPTION + ". 뒤로가기");

            int answer = InputHandler.readIntRange("번호를 선택해주세요",1,OPTION);


            if(answer == OPTION){
                run = false;
            }
            else {
                GameHub.getGameHub(answer).setOption(GAME_OPTION);
            }
        }
    }
}
