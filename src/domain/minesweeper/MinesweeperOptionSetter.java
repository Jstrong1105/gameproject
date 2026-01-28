package domain.minesweeper;

import util.InputHandler;

public class MinesweeperOptionSetter {

    private final InputHandler INPUT;
    private final MinesweeperOption OPTION;

    public MinesweeperOptionSetter(InputHandler input, MinesweeperOption option){

        INPUT = input;
        OPTION = option;
    }

    public void setOption()
    {
        boolean run = true;

        int back = MinesweeperOptionMenu.values().length + 1;

        while(run) {

            System.out.println("\n\n");

            System.out.println("====지뢰 옵션====");

            for(MinesweeperOptionMenu menu : MinesweeperOptionMenu.values()){
                System.out.println(menu.getNumber() + ". " + menu.getName() + " : " + menu.getExplain());
            }
            System.out.println(back + ". 뒤로가기");

            int answer = INPUT.readIntRange("번호를 선택해주세요.",1,back);

            if(answer == back){
                run = false;
            }
            else {
                MinesweeperOptionMenu.getMinesweeperOptionMenu(answer).setOption(INPUT,OPTION);
            }
        }
    }
}
