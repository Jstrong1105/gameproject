package engine;

import domain.base.GameOption;
import domain.base.GameProcess;
import domain.minesweeper.MinesweeperLauncher;
import domain.minesweeper.MinesweeperOptionSetter;
import util.InputHandler;

enum GameHub {

    MINESWEEPER(1,"지뢰찾기","폭탄이 아닌 칸을 전부 여세요!",
            (input,option) -> new MinesweeperLauncher(input,option.getMinesweeperOption()),
            (input, option) -> new MinesweeperOptionSetter(input, option.getMinesweeperOption()).setOption()
            )
    ;


    GameHub(int number, String name, String explain, GameMaker creator, OptionSetter setter){
        this.number = number;
        this.name = name;
        this.explain = explain;
        this.creator = creator;
        this.setter = setter;
    }

    private final int number;
    private final String name;
    private final String explain;
    private final GameMaker creator;
    private final OptionSetter setter;

    @FunctionalInterface
    interface GameMaker{
        GameProcess getGame(InputHandler input, GameOption option);
    }

    @FunctionalInterface
    interface OptionSetter{
        void setOption(InputHandler input, GameOption option);
    }

    int getNumber() { return number; }
    String getName() { return name; }
    String getExplain() { return explain; }

    GameProcess getGame(InputHandler input, GameOption option){
        return creator.getGame(input,option);
    }

    void setOption(InputHandler input, GameOption option){
        setter.setOption(input,option);
    }

    static GameHub getGameHub(int number){

        for(GameHub hub : GameHub.values()){
            if(hub.getNumber() == number){
                return hub;
            }
        }

        throw new IllegalArgumentException("존재하지 않는 번호입니다.");
    }
}
