package domain.base;

import util.InputHandler;

public class GameOptionSetter<T , E extends Enum<E> & GameOptionTemplate<T> > {

    private final T OPTION;
    private final E[] LIST;

    public GameOptionSetter(T option, E[] list){

        OPTION = option;
        LIST = list;
    }

    public void setOption()
    {
        boolean run = true;

        int back = LIST.length + 1;

        while(run) {

            System.out.println("\n\n");

            System.out.println("====옵션 설정====");

            for(E menu : LIST){
                System.out.println(menu.getNumber() + ". " + menu.getName() + " : " + menu.getExplain());
            }
            System.out.println(back + ". 뒤로가기");

            int answer = InputHandler.readIntRange("번호를 선택해주세요.",1,back);

            if(answer == back){
                run = false;
            }
            else {
                for(E menu : LIST){
                    if(menu.getNumber() == answer){
                        menu.setOption(OPTION);
                    }
                }
            }
        }
    }
}
