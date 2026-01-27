package domain.minesweeper;

import util.InputHandler;

public class MinesweeperOptionSetter {

    private final InputHandler input;
    private final MinesweeperOption option;

    public MinesweeperOptionSetter(InputHandler input, MinesweeperOption option){

        this.input = input;
        this.option = option;
    }

    public void setOption()
    {
        boolean run = true;

        int back = MinesweeperOptionMenu.values().length + 1;

        while(run) {

            System.out.println("====지뢰찾기옵션====");

            for(MinesweeperOptionMenu menu : MinesweeperOptionMenu.values()){
                System.out.println(menu.getNumber() + ". " + menu.getName() + " : " + menu.getExplain());
            }
            System.out.println(back + ". 뒤로가기");

            int answer = input.readIntRange("번호를 선택해주세요.",1,back);

            if(answer == back){
                run = false;
            }
            else {
                MinesweeperOptionMenu.getMinesweeperOptionMenu(answer).setOption(input,option);
            }
        }
    }
}
