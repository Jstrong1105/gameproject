package engine;

import domain.base.GameOption;
import domain.base.GameProcess;
import domain.memorygame.MemoryGameLauncher;
import domain.memorygame.MemoryGameOptionSetter;
import domain.minesweeper.MinesweeperLauncher;
import domain.minesweeper.MinesweeperOptionSetter;
import util.InputHandler;

enum GameHub {

    MINESWEEPER(1,"지뢰찾기","폭탄이 아닌 칸을 전부 여세요!",
            (input,option) -> new MinesweeperLauncher(input,option.getMinesweeperOption()),
            (input, option) -> new MinesweeperOptionSetter(input, option.getMinesweeperOption()).setOption()
            ),
    MEMORY_GAME(2,"메모리게임","같은 카드를 맞추세요!",
            (input,option) -> new MemoryGameLauncher(input,option.getMemoryGameOption()),
            (input,option) -> new MemoryGameOptionSetter(input,option.getMemoryGameOption()).setOption()
            )
    ;


    GameHub(int number, String name, String explain, GameMaker creator, OptionSetter setter){
        NUMBER = number;
        NAME = name;
        EXPLAIN = explain;
        CREATOR = creator;
        SETTER = setter;
    }

    private final int NUMBER;
    private final String NAME;
    private final String EXPLAIN;
    private final GameMaker CREATOR;
    private final OptionSetter SETTER;

    @FunctionalInterface
    interface GameMaker{
        GameProcess getGame(InputHandler input, GameOption option);
    }

    @FunctionalInterface
    interface OptionSetter{
        void setOption(InputHandler input, GameOption option);
    }

    int getNumber() { return NUMBER; }
    String getName() { return NAME; }
    String getExplain() { return EXPLAIN; }

    GameProcess getGame(InputHandler input, GameOption option){
        return CREATOR.getGame(input,option);
    }

    void setOption(InputHandler input, GameOption option){
        SETTER.setOption(input,option);
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
