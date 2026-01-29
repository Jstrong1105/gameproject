package domain.memorygame;

import util.InputHandler;

public class MemoryGameOptionSetter {

    private final InputHandler INPUT;
    private final MemoryGameOption OPTION;

    public MemoryGameOptionSetter(InputHandler input, MemoryGameOption option){
        INPUT = input;
        OPTION = option;
    }

    public void setOption()
    {
        boolean run = true;

        int back = MemoryGameOptionMenu.values().length + 1;

        while(run) {

            System.out.println("\n\n");

            System.out.println("====메모리 옵션====");

            for(MemoryGameOptionMenu menu : MemoryGameOptionMenu.values()){
                System.out.println(menu.getNumber() + ". " + menu.getName() + " : " + menu.getExplain());
            }
            System.out.println(back + ". 뒤로가기");

            int answer = INPUT.readIntRange("번호를 선택해주세요.",1,back);

            if(answer == back){
                run = false;
            }
            else {
                MemoryGameOptionMenu.getMemoryGameOptionMenu(answer).setOption(INPUT,OPTION);
            }
        }
    }
}
