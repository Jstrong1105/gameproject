package domain.base;

import domain.memorygame.MemoryGameOption;
import domain.minesweeper.MinesweeperOption;
import domain.pokergamble.PokerGambleOption;

public class GameOption {

    private MinesweeperOption minesweeperOption;
    public MinesweeperOption getMinesweeperOption()
    {
        if (minesweeperOption == null){
            minesweeperOption = new MinesweeperOption();
        }
        return minesweeperOption;
    }

    private MemoryGameOption memoryGameOption;
    public MemoryGameOption getMemoryGameOption()
    {
        if(memoryGameOption == null){
            memoryGameOption = new MemoryGameOption();
        }
        return memoryGameOption;
    }

    private PokerGambleOption pokerGambleOption;
    public PokerGambleOption getPokerGambleOption()
    {
        if(pokerGambleOption == null){
            pokerGambleOption = new PokerGambleOption();
        }
        return pokerGambleOption;
    }
}
