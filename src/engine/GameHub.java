package engine;

import domain.base.GameOption;
import domain.base.GameOptionSetter;
import domain.base.GameProcess;
import domain.memorygame.MemoryGameLauncher;
import domain.memorygame.MemoryGameOptionMenu;
import domain.minesweeper.MinesweeperLauncher;
import domain.minesweeper.MinesweeperOptionMenu;
import domain.pokergamble.PokerGambleLauncher;
import domain.pokergamble.PokerGambleOptionMenu;

public enum GameHub {

    MINESWEEPER(1,"지뢰찾기","폭탄이 아닌 칸을 전부 여세요!",
            (option)
                    -> new MinesweeperLauncher(option.getMinesweeperOption()),
            (option)
                    -> new GameOptionSetter<>(option.getMinesweeperOption(), MinesweeperOptionMenu.values()).setOption()
            ),
    MEMORY_GAME(2,"메모리게임","같은 카드를 맞추세요!",
            (option)
                    -> new MemoryGameLauncher(option.getMemoryGameOption()),
            (option)
                    -> new GameOptionSetter<>(option.getMemoryGameOption(),MemoryGameOptionMenu.values()).setOption()
            ),
    POKER_GAMBLE(3,"포커겜블","포커 게임에서 승리해 목표 코인을 달성하세요!",
            (option)
                    -> new PokerGambleLauncher(option.getPokerGambleOption()),
            (option)
                    -> new GameOptionSetter<>(option.getPokerGambleOption(),PokerGambleOptionMenu.values()).setOption()
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
        GameProcess getGame(GameOption option);
    }

    @FunctionalInterface
    interface OptionSetter{
        void setOption(GameOption option);
    }

    int getNumber() { return NUMBER; }
    String getName() { return NAME; }
    String getExplain() { return EXPLAIN; }

    GameProcess getGame(GameOption option){
        return CREATOR.getGame(option);
    }

    void setOption(GameOption option){
        SETTER.setOption(option);
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