package domain.base;

import domain.minesweeper.MinesweeperOption;

public class GameOption {

    private MinesweeperOption minesweeperOption;
    public MinesweeperOption getMinesweeperOption()
    {
        if (minesweeperOption == null){
            minesweeperOption = new MinesweeperOption();
        }
        return minesweeperOption;
    }

    /*
    private MemoryGameOption memoryGameOption;
    public MemoryGameOption getMemoryGameOption()
    {
        if(memoryGameOption == null){
            memoryGameOption = new MemoryGameOption();
        }
        return memoryGameOption;
    }
    */
}
